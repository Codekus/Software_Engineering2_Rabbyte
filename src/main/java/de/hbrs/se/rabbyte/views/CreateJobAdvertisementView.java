package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "create_JobAdvert")
@PageTitle("Neue Stellenausschreibung")
public class CreateJobAdvertisementView {

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

    //public CreateJobAdvertisementView(JobAdvertControl jobAdvertControl){}
}
