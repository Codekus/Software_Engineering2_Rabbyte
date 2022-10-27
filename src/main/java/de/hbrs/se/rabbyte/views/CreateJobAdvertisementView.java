package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "create_JobAdvert", layout = AppView.class)
@PageTitle("Neue Stellenausschreibung")
public class CreateJobAdvertisementView extends Div {

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

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        type.setAllowCustomValue(true);

        binder.bindInstanceFields(this);
        clearForm();

        back.addClickListener(event -> clearForm());

        save.addClickListener(e -> {
            // Speicherung der Daten über das zuhörige Control-Object.
            BusinessDTO businessDTO = (BusinessDTO) UI.getCurrent().getSession().getAttribute("current_user");
            jobAdvertControl.createJobAdvert(binder.getBean() ,  businessDTO );

            Notification.show("Veröffentlicht");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new JobAdvertisementDTOImpl());
    }

    private Component createTitle() {
        return new H3("Stellenausschreibung");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(title, type, description);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(back);
        return buttonLayout;
    }
}
