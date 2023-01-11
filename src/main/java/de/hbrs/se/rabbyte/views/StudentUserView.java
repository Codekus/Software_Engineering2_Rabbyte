package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.control.JobAdvertControl;
import de.hbrs.se.rabbyte.control.StudentControl;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;

@Route("settings")
@PageTitle("Account Einstellungen")
@Theme(value = Lumo.class)
public class StudentUserView extends VerticalLayout //implements View
{

    StudentControl studentControl;

    private Binder<StudentDTOImpl> binder = new Binder<>(StudentDTOImpl.class);
    @Autowired
    SecurityService securityService;
    @Autowired
    CrmService service;
    //GeneralUser attributes
    EmailField emailField = new EmailField("Email");
    TextField plz = new TextField("PLZ");
    TextField city = new TextField("City");
    TextField state = new TextField("State");
    TextField street = new TextField("Street");
    TextField streetNr = new TextField("StreetNr");

    //Student attributes
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField faculty = new TextField("Faculty");
    //   private TextField birthDate = new TextField("Date of Birth");
    Tab studentTab;
    Button cancel;
    Button save;

    VerticalLayout verticalLayout;
    VerticalLayout tabsLayout;

    class StudentForm extends Div {

        StudentForm() {
            emailField.setRequiredIndicatorVisible(true);
            //emailField.setValue(student.getEmail());
            firstName.setRequiredIndicatorVisible(true);
            //firstName.setValue(student.getFirstName());
            lastName.setRequiredIndicatorVisible(true);
            //lastName.setValue(student.getLastName());
    //        binder.bindInstanceFields(this);

            FormLayout formLayout = new FormLayout();
            formLayout.setMaxWidth("80 vw");
            formLayout.add(emailField, firstName, lastName, faculty);// plz, street, streetNr, state,

            this.add(formLayout);
        }


    }

    public StudentUserView() {

        verticalLayout = new VerticalLayout();
        tabsLayout = new VerticalLayout();
        H1 h1 = new H1("User details");

        StudentForm studentForm = new StudentForm();

        verticalLayout.add(h1);
        verticalLayout.setMaxWidth("80 vw");
        save = new Button("Save");
        save.addClickListener(e -> {
            studentControl.editStudent(createUpdatedStudentDTO());
        //    Notification.show("Stellausschreibung geändert");
        //    NavigationUtil.toMainView();
        });

        cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            clearForm();
            Notification.show("User form cleared.");
        });

        studentTab = new Tab("Student");
        Tabs tabs = new Tabs(studentTab);

        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );

        setContent(tabs.getSelectedTab());
        verticalLayout.add(h1);
        add(verticalLayout);
        add(tabs, tabsLayout);

    }

    private StudentDTO createUpdatedStudentDTO() {
        StudentDTOImpl studentDTO = new StudentDTOImpl();
        studentDTO.setEmail(emailField.getValue());
        studentDTO.setFirstName(firstName.getValue());
        studentDTO.setLastName(lastName.getValue());
        studentDTO.setFaculty(faculty.getValue());
        return studentDTO;
    }

    private void setContent(Tab tab) {

        tabsLayout.removeAll();

        tabsLayout.add(new StudentUserView.StudentForm());
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setAlignItems(Alignment.START);
        buttonLayout.add(save, cancel);
        tabsLayout.add(buttonLayout);


    }

    private void clearForm() {
        emailField.setValue("");
        firstName.setValue("");
        lastName.setValue("");
        plz.setValue("");
        street.setValue("");
        streetNr.setValue("");
        city.setValue("");
        state.setValue("");
        faculty.setValue("");
    }
}
