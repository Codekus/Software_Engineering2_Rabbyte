package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Navigation;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import de.hbrs.se.rabbyte.control.ApplicationControl;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import de.hbrs.se.rabbyte.control.MessageControl;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.NoSuchElementException;

@org.springframework.stereotype.Component
@Scope("prototype")
@Route(value="application")
@PageTitle("Bewerbung")
public class ApplicationView extends Div implements HasUrlParameter<Integer> {

    @Autowired
    SecurityService securityService;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    ApplicationControl applicationControl;
    @Autowired
    MessageControl messageControl;

    private CrmService service;

    private Integer busID;

    private TextArea title = new TextArea("Betreff");
    private TextArea description = new TextArea("Anschreiben");
    private Button apply = new Button("Bewerben");
    private Button back = new Button("Zurück");
    private TextArea requirements = new TextArea("Anforderungsprofil & Qualifikationen");
    //private PhoneNumberField phone = new PhoneNumberField("Telefonnummer");


    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer parameter) {
        try {
            if ((parameter == null) || service.findJobAdvertisementById(parameter) == null) {
                beforeEvent.forwardTo("");
            } else {
                busID = service.findJobAdvertisementById(parameter).getBusiness().getId();
            }
        } catch (NoSuchElementException ex) {
            if (parameter != null) {
                Notification.show(parameter + " does not exist.");
            } else {
                Notification.show("Invalid input.");
            }
            beforeEvent.forwardTo("");

        }
        busID = service.findJobAdvertisementById(parameter).getBusiness().getId();

    }

    public ApplicationView(CrmService service){
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
            /*
            if(!validate().isEmpty()){
                Notification.show(validate().toString());
            }else{
                // Speicherung der Daten über das zuhörige Control-Object.
                jobAdvertControl.createJobAdvert(createNewJobAdvert());

                Notification.show("Veröffentlicht");
                clearForm();
            }*/
            apply();
        });
    }


    public void apply(){
        MessageDTOImpl messageDTO = new MessageDTOImpl();

        applicationControl.apply(messageControl, messageDTO, busID, title.getValue(), description.getValue());
        NavigationUtil.toMainView();
    }

    /*
    public List<String> validate(){
        List<String> validation = new ArrayList<>();
        JobAdvertControl jobAdvertControl = new JobAdvertControl();
        if(jobAdvertControl.validateDescription(description.getValue()) == false){
            validation.add("Invalid Description!");        }
        return validation;
    }*/

    private void clearForm() {
        title.setValue("");
        description.setValue("");
    }


    private Component createFormLayout() {
        VerticalLayout vert3 = new VerticalLayout();
        HorizontalLayout hor2 = new HorizontalLayout();

        hor2.add(description);
        hor2.setHeightFull();
        vert3.add(title, hor2);
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

