package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@Route(value = "registration" )
public class RegistrationView extends VerticalLayout {


    //Basic User Fields
    EmailField emailField = new EmailField("E-Mail");
    PasswordField passwordField = new PasswordField("Password");
    PasswordField passwordFieldRepeat = new PasswordField("Password wiederholen");


    class UserForm extends Div {

        UserForm() {
            emailField.setRequiredIndicatorVisible(true);
            passwordField.setRequiredIndicatorVisible(true);
            passwordFieldRepeat.setRequiredIndicatorVisible(true);

            passwordField.setMinLength(5);
            passwordFieldRepeat.setMinLength(5);

            FormLayout formLayout = new FormLayout();
            formLayout.add(emailField , passwordField , passwordFieldRepeat);

            this.add(formLayout);
        }

    }

    public RegistrationView() {

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setWidth("80%");
        H1 h1 = new H1("Registrierung");

        UserForm form = new UserForm();

        verticalLayout.add(h1,form);
        add(verticalLayout);

    }
}
