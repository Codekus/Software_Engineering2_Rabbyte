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
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.control.StudentControl;
import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.control.factory.VerificationFactory;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationResultDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationStudentDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.EmailSenderService;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Route("settings")
@PageTitle("Account Einstellungen")
@Theme(value = Lumo.class)
public class StudentUserView extends VerticalLayout  implements BeforeEnterObserver//implements View
{
    PersonRepository personRepository;
    @Autowired
    StudentControl studentControl;
    @Autowired
    SecurityService securityService;
    @Autowired
    CrmService service;
    //GeneralUser attributes
    EmailField emailField = new EmailField("Email");
    PasswordField passwordField = new PasswordField("Password");
    PasswordField passwordFieldRepeat = new PasswordField("PasswordRepeat");
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
            formLayout.add(emailField, passwordField, passwordFieldRepeat, firstName, lastName, faculty);// plz, street, streetNr, state,
            initTextFields();
            this.add(formLayout);
        }


    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        verticalLayout = new VerticalLayout();
        tabsLayout = new VerticalLayout();
        H1 h1 = new H1("User details");

        verticalLayout.add(h1);
        verticalLayout.setMaxWidth("80 vw");
        save = new Button("Save");
        save.addClickListener(e -> {
            try {
                if(!passwordField.getValue().equals(passwordFieldRepeat.getValue())){
                    Notification.show("Passwörter müssen gleich sein!");
                } else if (!securityService.getAuthenticatedUser().getEmail().equals(emailField.getValue())) {
                    try{
                        String email = personRepository.findByEmail(emailField.getValue()).getEmail();
                    }catch (NullPointerException ex){
                        Notification.show("Email wird bereits verwendet");
                    }
                } else{
                    studentControl.editStudent(createUpdatedStudentDTO());
                    Notification.show("Profil überarbeitet");
                    NavigationUtil.toMainView();
                }
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        });

        cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            clearForm();
            Notification.show("User form cleared.");
        });

        studentTab = new Tab("Student");
        Tabs tabs = new Tabs(studentTab);

        tabs.addSelectedChangeListener(e ->
                setContent(e.getSelectedTab())
        );

        setContent(tabs.getSelectedTab());
        verticalLayout.add(h1);
        add(verticalLayout);
        add(tabs, tabsLayout);

    }

    private StudentDTO createUpdatedStudentDTO() throws InvalidKeySpecException, NoSuchAlgorithmException {
        StudentDTOImpl studentDTO = new StudentDTOImpl();
        studentDTO.setId(securityService.getAuthenticatedUserID());
        studentDTO.setEmail(emailField.getValue());
        studentDTO.setPassword(securityService.saltPassword(securityService.getAuthenticatedUser().getEmail(), passwordField.getValue()));
        studentDTO.setFirstName(firstName.getValue());
        studentDTO.setLastName(lastName.getValue());
        studentDTO.setFaculty(faculty.getValue());
        return studentDTO;
    }
    private void initTextFields(){
        StudentDTO studentDTO = studentControl.getStudent(securityService.getAuthenticatedUserID());

        emailField.setValue(securityService.getAuthenticatedUser().getEmail());
        firstName.setValue(studentDTO.getFirstName());
        lastName.setValue(studentDTO.getLastName());
        faculty.setValue(studentDTO.getFaculty());
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
