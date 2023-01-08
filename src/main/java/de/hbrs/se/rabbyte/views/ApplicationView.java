package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import de.hbrs.se.rabbyte.control.MessageControl;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;


@PageTitle("Bewerbung")
public class ApplicationView extends Div {

    @Autowired
    SecurityService securityService;

    @Autowired
    BusinessRepository businessRepository;

    private TextArea description = new TextArea("Anschreiben");
    private Button apply = new Button("Bewerben");
    private Button back = new Button("Zurück");
    private TextArea requirements = new TextArea("Anforderungsprofil & Qualifikationen");
    //private PhoneNumberField phone = new PhoneNumberField("Telefonnummer");



    public ApplicationView(){
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


    public MessageDTO apply(){
        MessageDTOImpl messageDTO = new MessageDTOImpl();
        MessageControl messageControl = new MessageControl();

        messageDTO.setMessageText(description.getValue());
        try {
            messageControl.sendMessage(messageDTO);
        } catch (DatabaseUserException e) {
            throw new RuntimeException(e);
        }

        return messageDTO;
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

