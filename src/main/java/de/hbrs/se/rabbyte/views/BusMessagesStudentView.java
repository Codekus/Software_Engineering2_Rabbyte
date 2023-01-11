package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import de.hbrs.se.rabbyte.control.ApplicationControl;
import de.hbrs.se.rabbyte.control.MessageControl;
import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.dtos.implemented.ApplicationDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static de.hbrs.se.rabbyte.control.factory.JobAdvertFactory.publishJobAdvert;


@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("Student anschreiben")
public class BusMessagesStudentView extends Div implements HasUrlParameter<Integer> {

    @Autowired
    SecurityService securityService;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    ApplicationControl applicationControl;
    @Autowired
    MessageControl messageControl;

    private CrmService service;
    private JobAdvertisementDTO jobAdvertisementDTO;

    private TextArea description = new TextArea("Anschreiben");
    private Button apply = new Button("Bewerben");
    private Button back = new Button("Zurück");
    private TextArea requirements = new TextArea("Anforderungsprofil & Qualifikationen");
    private final StudentRepository studentRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer parameter) {
        try {
            if ((parameter == null) || service.findJobAdvertisementById(parameter) == null) {
                beforeEvent.forwardTo("");
            } else {
                jobAdvertisementDTO = service.findJobAdvertisementById(parameter);
            }
        } catch (NoSuchElementException ex) {
            if (parameter != null) {
                Notification.show(parameter + " does not exist.");
            } else {
                Notification.show("Invalid input.");
            }
            beforeEvent.forwardTo("");

        }

    }

    public BusMessagesStudentView(CrmService service,
                           StudentRepository studentRepository){
        this.service = service;
        setSizeFull();

        addClassName("application-view");

        //add(createButtonLayoutBack());
        add(createFormLayout());
        add(createButtonLayoutSubmit());

        description.setWidth("550px");
        description.setMinHeight("300px");
        description.setMaxHeight("450px");

        clearForm();

        back.addClickListener(event -> clearForm());

        apply.addClickListener(e -> {

            if(!validate().isEmpty()){
                Notification.show(validate().toString());
            }else{
                // Speicherung der Daten über das zuhörige Control-Object.

                Notification.show("Bewerbung abgeschickt");
                apply();
            }
        });
        this.studentRepository = studentRepository;
    }

    public ApplicationDTO createNewApplication(){
        ApplicationDTOImpl applicationDTO = new ApplicationDTOImpl();

        applicationDTO.setApplicationText(description.getValue());
        applicationDTO.setDate(LocalDate.now());
        applicationDTO.setJobAdvertisement(publishJobAdvert(jobAdvertisementDTO));

        StudentDTO studentDTO = studentRepository.findStudentById(securityService.getAuthenticatedUser().getId());
        Student student = PersonFactory.createStudent(studentDTO);
        applicationDTO.setStudent(student);    // <- userID

        return applicationDTO;
    }


    public void apply(){
        MessageDTOImpl messageDTO = new MessageDTOImpl();
        ApplicationDTO applicationDTO = createNewApplication();

        applicationControl.apply(messageControl, messageDTO, applicationDTO);
        NavigationUtil.toMainView();
    }


    public List<String> validate(){
        List<String> validation = new ArrayList<>();
        ApplicationControl applicationControl = new ApplicationControl();
        if(!applicationControl.validateDescription(description.getValue())){
            validation.add("Invalid Description!");        }
        return validation;
    }

    private void clearForm() {
        description.setValue("");
    }


    private Component createFormLayout() {
        VerticalLayout vert3 = new VerticalLayout();
        HorizontalLayout hor2 = new HorizontalLayout();

        hor2.add(description);
        hor2.setHeightFull();
        vert3.add(hor2);
        vert3.setWidthFull();
        vert3.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return vert3;
    }

    private Component createButtonLayoutSubmit() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        VerticalLayout buttonLayoutVert = new VerticalLayout();
        buttonLayout.addClassName("button-layout");
        apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(apply);
        buttonLayoutVert.add(buttonLayout);
        buttonLayout.setAlignSelf(FlexComponent.Alignment.valueOf("END"));
        //buttonLayout.setAlignItems(FlexComponent.Alignment.valueOf("END"));
        buttonLayoutVert.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
        return buttonLayoutVert;
    }
}

