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
import com.vaadin.flow.router.*;
import de.hbrs.se.rabbyte.control.JobAdvertControl;

import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;

import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("Job Ausschreibung bearbeiten")
@Route(value = "edit-jobadvert")
public class JobAdvertEditView extends VerticalLayout implements HasUrlParameter<Integer>, AfterNavigationObserver {

    private TextField title = new TextField("Stellen Titel");
    private TextArea description = new TextArea("Stellenbeschreibung");
    private ComboBox<String> type = new ComboBox<>("Beschäftigungsart");
    private Button save = new Button("Änderungen speichern");

    private Binder<JobAdvertisementDTOImpl> binder = new Binder<>(JobAdvertisementDTOImpl.class);

    JobAdvertisementDTO jobAdvertisement;

    @Autowired
    SecurityService securityService;

    @Autowired
    JobAdvertControl jobAdvertControl;

    @Autowired
    CrmService service;


    public JobAdvertEditView(){
        addClassName("create-jobAdvert-view");
        type.setItems("Vollzeit", "Teilzeit", "Praktikum", "Projektarbeit", "Bachelor/ Master");



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
                jobAdvertControl.editJobAdvert(createUpdatedJobAdvertDTO());
                Notification.show("Stellausschreibung geändert");
                NavigationUtil.toMainView();
            }
        });

    }

    private JobAdvertisementDTO createUpdatedJobAdvertDTO(){
        JobAdvertisementDTOImpl jobAdvertisementDTO = new JobAdvertisementDTOImpl();

        jobAdvertisementDTO.setBusiness(jobAdvertisement.getBusiness());
        jobAdvertisementDTO.setId(jobAdvertisement.getId());
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

        buttonLayoutVert.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
        return buttonLayoutVert;
    }

    private Component createTitle() {

        VerticalLayout layoutVer = new VerticalLayout();
        layoutVer.add(new H3("Stellenausschreibung: \n'" + jobAdvertisement.getTitle() + "'\n ändern"));
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
        title.setValue(jobAdvertisement.getTitle());
        description.setValue(jobAdvertisement.getText());
        type.setValue(jobAdvertisement.getType());

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer jobAdID) {
        try {
            JobAdvertisementDTO jobAd = service.findJobAdvertisementById(jobAdID);
            int userid = securityService.getAuthenticatedUserID();
            if(jobAd == null || jobAd.getBusiness().getId() != userid)
                beforeEvent.forwardTo("");

            this.jobAdvertisement = jobAd;
        } catch (NullPointerException e) {
            // Die paramRequestedBusinessId ist kein valider Integer! => reroute to main
            beforeEvent.forwardTo("");
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayoutSubmit());
    }
}
