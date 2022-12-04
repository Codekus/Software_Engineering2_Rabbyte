package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Job Ausschreibung bearbeiten")
public class JobAdvertEditView extends VerticalLayout {

    private TextField title = new TextField("Stellen Titel");
    private TextArea description = new TextArea("Stellenbeschreibung");
    private ComboBox<String> type = new ComboBox<>("Beschäftigungsart");
    private Button save = new Button("Änderungen speichern");

    private Binder<JobAdvertisementDTOImpl> binder = new Binder<>(JobAdvertisementDTOImpl.class);

    @Autowired
    SecurityService securityService;

    @Autowired
    BusinessRepository businessRepository;

    JobAdvertControl jobAdvertControl;

    JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertEditView(JobAdvertControl jobAdvertControl, JobAdvertisementRepository jobAdvertisementRepository){
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.jobAdvertControl = jobAdvertControl;
        addClassName("create-jobAdvert-view");
        type.setItems("Vollzeit", "Teilzeit", "Praktikum", "Projektarbeit", "Bachelor/ Master");
        //add(createButtonLayoutBack());
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayoutSubmit());


        type.setWidth("200px");
        title.setWidth("300px");
        description.setWidth("550px");
        description.setMinHeight("300px");
        description.setMaxHeight("450px");

        binder.bindInstanceFields(this);

        save.addClickListener(e -> {
            if(!validate().isEmpty()){
                Notification.show(validate().toString());
            }else{
                // Speicherung der Daten über das zuhörige Control-Object.
                jobAdvertControl.createJobAdvert(createUpdatedJobAdvertDTO());

                Notification.show("Stellausschreibung geändert");
            }
        });

    }

    private JobAdvertisementDTO createUpdatedJobAdvertDTO(){
        JobAdvertisementDTOImpl jobAdvertisementDTO = new JobAdvertisementDTOImpl();
        BusinessDTO businessDTO = businessRepository.findBusinessByBusinessID(securityService.getAuthenticatedUser().getId());
        Business business = PersonFactory.createBusiness(businessDTO);
        jobAdvertisementDTO.setBusiness(business);
        jobAdvertisementDTO.setId(30000275);
        jobAdvertisementDTO.setTitle(title.getValue());
        jobAdvertisementDTO.setType(type.getValue());
        jobAdvertisementDTO.setText(description.getValue());

        return jobAdvertisementDTO;
    }

    public List<String> validate(){

        List<String> validation = new ArrayList<>();

        if(!jobAdvertControl.validateTitle(title.getValue())){
            validation.add("Invalid Title!");
        }
        if(!jobAdvertControl.validateType(type.getValue())){
            validation.add("Invalid Type!");        }
        if(!jobAdvertControl.validateDescription(description.getValue())){
            validation.add("Invalid Description!");        }
        return validation;


    }

    private Component createButtonLayoutSubmit() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        VerticalLayout buttonLayoutVert = new VerticalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayoutVert.add(buttonLayout);
        buttonLayout.setAlignSelf(FlexComponent.Alignment.valueOf("END"));
        //buttonLayout.setAlignItems(FlexComponent.Alignment.valueOf("END"));
        buttonLayoutVert.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
        return buttonLayoutVert;
    }

    private Component createTitle() {
        JobAdvertisementDTO oldJobAdvertDTO = jobAdvertisementRepository.findJobAdvertisementById(30000275);
        VerticalLayout layoutVer = new VerticalLayout();
        layoutVer.add(new H3("Stellenausschreibung: \n'" + oldJobAdvertDTO.getTitle() + "'\n ändern"));
        layoutVer.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
        return layoutVer;
    }

    private Component createFormLayout() {
        VerticalLayout vert1 = new VerticalLayout();
        VerticalLayout vert2 = new VerticalLayout();
        VerticalLayout vert3 = new VerticalLayout();
        HorizontalLayout hor1 = new HorizontalLayout();
        HorizontalLayout hor2 = new HorizontalLayout();


        initTextFields();

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

    private void initTextFields(){
        JobAdvertisementDTO oldJobAdvertDTO = jobAdvertisementRepository.findJobAdvertisementById(30000275);

        title.setValue(oldJobAdvertDTO.getTitle());
        description.setValue(oldJobAdvertDTO.getText());
        type.setValue(oldJobAdvertDTO.getType());

    }

}
