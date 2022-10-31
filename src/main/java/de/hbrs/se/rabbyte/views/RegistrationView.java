package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.control.RegistrationControl;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "registration" )
public class RegistrationView extends VerticalLayout {

    @Autowired
    private RegistrationControl registrationControl;


    //Basic User Fields
    EmailField emailField = new EmailField("E-Mail");
    PasswordField passwordField = new PasswordField("Password");
    PasswordField passwordFieldRepeat = new PasswordField("Password wiederholen");

    //TextField plzField = new TextField("Plz");

    //TextField cityField = new TextField("Stadt");

    //TextField countryField = new TextField("Land");

    //TextField streetField = new TextField( "Street");

    //IntegerField streetNumber = new IntegerField ("Hausnummer");

    // StudentUser

    //TextField facultyField = new TextField("Fachbereich");

    TextField firstName = new TextField("Vorname");

    TextField lastName = new TextField("Nachnahme");
    class UserForm extends Div {

        UserForm() {
            emailField.setRequiredIndicatorVisible(true);
            passwordField.setRequiredIndicatorVisible(true);
            passwordFieldRepeat.setRequiredIndicatorVisible(true);
            firstName.setRequiredIndicatorVisible(true);
            lastName.setRequiredIndicatorVisible(true);
            passwordField.setMinLength(5);
            passwordFieldRepeat.setMinLength(5);



            FormLayout formLayout = new FormLayout();
            formLayout.add(emailField, passwordField, passwordFieldRepeat, firstName,lastName);

            this.add(formLayout);
        }

        public StudentDTOImpl createNewStudentDTO() {
            StudentDTOImpl newStudent = new StudentDTOImpl();
            newStudent.setEmail(emailField.getValue());
            newStudent.setPassword(passwordField.getValue());
            newStudent.setFirstName(firstName.getValue());
            newStudent.setLastName(lastName.getValue());

            return newStudent;
        }
    }



    public RegistrationView() {

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setWidth("60%");
        H1 h1 = new H1("Registrierung");

        UserForm form = new UserForm();

        Button register_button = new Button("Registrieren");

        verticalLayout.add(h1,form,register_button);
        add(verticalLayout);

        register_button.addClickListener( e -> {
            StudentDTOImpl dto = form.createNewStudentDTO();

            registrationControl.registerStudent(dto);
        });
    }
}
