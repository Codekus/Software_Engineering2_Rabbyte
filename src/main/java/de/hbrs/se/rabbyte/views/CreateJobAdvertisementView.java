package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@PageTitle("Neue Stellenausschreibung")
public class CreateJobAdvertisementView extends Div {

    @Autowired
    SecurityService securityService;

    @Autowired
    BusinessRepository businessRepository;

    //ToDo for first sprint
    private TextField title = new TextField("Stellen Titel");
    private TextArea description = new TextArea("Stellenbeschreibung");
    private ComboBox<String> type = new ComboBox<>("Beschäftigungsart");
    private Button save = new Button("Veröffentlichen");
    private Button back = new Button("Zurück");

    //Ideas for 2nd sprint
    private TextField contact = new TextField("Kontaktperson");
    //Eventuell custom field zur überprüfung, dass die mail legit ist
    private TextField mail = new TextField("E-Mail-Adresse");
    private TextArea requirements = new TextArea("Anforderungsprofil & Qualifikationen");
    private DatePicker applicationTime = new DatePicker("Bewerbungsfrist");
    //ToDo PhoneNumberField innere class erstellen
    //private PhoneNumberField phone = new PhoneNumberField("Telefonnummer");


    private Binder<JobAdvertisementDTOImpl> binder = new Binder(JobAdvertisementDTOImpl.class);

    public CreateJobAdvertisementView(JobAdvertControl jobAdvertControl){
        addClassName("create-jobAdvert-view");

        //add(createButtonLayoutBack());
        add(createTitle());
        add(createFormLayout());

        HorizontalLayout buttonLayout = new HorizontalLayout();

        VerticalLayout buttonLayoutVert = new VerticalLayout();

        buttonLayout.addClassName("button-layout");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonLayout.add(save);

        buttonLayoutVert.add(buttonLayout);

        buttonLayout.setAlignSelf(FlexComponent.Alignment.valueOf("END"));

        //buttonLayout.setAlignItems(FlexComponent.Alignment.valueOf("END"));

        buttonLayoutVert.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));

        add(buttonLayoutVert);

        type.setItems("Vollzeit", "Teilzeit", "Praktikum", "Projektarbeit", "Bachelor/ Master");
        type.setWidth("200px");
        title.setWidth("300px");
        description.setWidth("550px");
        description.setMinHeight("300px");
        description.setMaxHeight("450px");

        binder.bindInstanceFields(this);
        clearForm();

        back.addClickListener(event -> clearForm());

        save.addClickListener(e -> {
            if(!validate().isEmpty()){
                Notification.show(validate().toString());
            }else{
                // Speicherung der Daten über das zuhörige Control-Object.
                jobAdvertControl.createJobAdvert(createNewJobAdvert());

                Notification.show("Veröffentlicht");
                clearForm();
            }
        });
    }


    public JobAdvertisementDTO createNewJobAdvert(){
        JobAdvertisementDTOImpl jobAdvertisementDTO = new JobAdvertisementDTOImpl();
        jobAdvertisementDTO.setTitle(title.getValue());
        jobAdvertisementDTO.setType(type.getValue());
        jobAdvertisementDTO.setText(description.getValue());

        BusinessDTO businessDTO = businessRepository.findBusinessByBusinessID(securityService.getAuthenticatedUser().getId());
        Business business = PersonFactory.createBusiness(businessDTO);
        jobAdvertisementDTO.setBusiness(business);    // <- userID

        return jobAdvertisementDTO;
    }

    public List<String> validate(){
        List<String> validation = new ArrayList<>();
        JobAdvertControl jobAdvertControl = new JobAdvertControl();
        if(jobAdvertControl.validateTitle(title.getValue()) == false){
            validation.add("Invalid Title!");
        }
        if(jobAdvertControl.validateType(type.getValue()) == false){
            validation.add("Invalid Type!");        }
        if(jobAdvertControl.validateDescription(description.getValue()) == false){
            validation.add("Invalid Description!");        }
        return validation;
    }

    private void clearForm() {
        title.setValue("");
        type.setValue("");
        description.setValue("");
    }

    private Component createTitle() {
        VerticalLayout layoutVer = new VerticalLayout();
        layoutVer.add(new H3("Stellenausschreibung"));
        layoutVer.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
        return layoutVer;
    }

    private Component createFormLayout() {
        VerticalLayout vert1 = new VerticalLayout();
        VerticalLayout vert2 = new VerticalLayout();
        VerticalLayout vert3 = new VerticalLayout();
        HorizontalLayout hor1 = new HorizontalLayout();
        HorizontalLayout hor2 = new HorizontalLayout();

        vert1.add(title);
        vert2.add(type);
        hor1.add(vert1, vert2);
        hor2.add(description);
        hor2.setHeightFull();
        vert3.add(hor1, hor2);
        vert3.setWidthFull();
        vert3.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return vert3;
    }



    /*
    private Component createButtonLayoutBack() {
        FormLayout buttonLayout = new FormLayout();
        buttonLayout.add(back);
        buttonLayout.setWidth("100px");
        return buttonLayout;
    }

     */
}

