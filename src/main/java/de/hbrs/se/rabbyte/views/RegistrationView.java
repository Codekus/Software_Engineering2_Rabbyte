package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.control.RegistrationControl;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.implemented.BusinessDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationBusinessDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationStudentDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = "registration" )
@Theme(value = Lumo.class)
public class RegistrationView extends VerticalLayout {

    @Autowired
    private RegistrationControl registrationControl;


    //Student Fields
    EmailField emailFieldStudent = new EmailField("E-Mail");
    PasswordField passwordFieldStudent = new PasswordField("Password");
    PasswordField passwordFieldRepeatStudent = new PasswordField("Password wiederholen");
    TextField firstNameStudent = new TextField("Vorname");
    TextField lastNameStudent = new TextField("Nachnahme");


    //Business Fields
    EmailField emailFieldBusiness = new EmailField("E-Mail");
    PasswordField passwordFieldBusiness = new PasswordField("Password");
    PasswordField passwordFieldRepeatBusiness = new PasswordField("Password wiederholen");
    TextField businessName = new TextField("Business");


    //Tabs
    private final Tab student;
    private final Tab business;

    //Buttons
    Button registerButtonStudent;
    Button registerButtonBusiness;

    //Layouts
    VerticalLayout verticalLayout;
    VerticalLayout tabsLayout;
    class StudentForm extends Div {

        StudentForm() {
            emailFieldStudent.setRequiredIndicatorVisible(true);
            passwordFieldStudent.setRequiredIndicatorVisible(true);
            passwordFieldRepeatStudent.setRequiredIndicatorVisible(true);
            firstNameStudent.setRequiredIndicatorVisible(true);
            lastNameStudent.setRequiredIndicatorVisible(true);
            passwordFieldStudent.setMinLength(5);
            passwordFieldRepeatStudent.setMinLength(5);


            FormLayout formLayout = new FormLayout();
            formLayout.add(emailFieldStudent, passwordFieldStudent, passwordFieldRepeatStudent, firstNameStudent, lastNameStudent);

            this.add(formLayout);
        }

        public StudentDTOImpl createNewStudentDTO() {
            StudentDTOImpl newStudent = new StudentDTOImpl();
            newStudent.setEmail(emailFieldStudent.getValue());
            newStudent.setPassword(passwordFieldStudent.getValue());
            newStudent.setFirstName(firstNameStudent.getValue());
            newStudent.setLastName(lastNameStudent.getValue());

            return newStudent;
        }
    }

    class BusinessForm extends Div {


        BusinessForm() {

        emailFieldBusiness.setRequiredIndicatorVisible(true);
        passwordFieldBusiness.setRequiredIndicatorVisible(true);
        passwordFieldRepeatBusiness.setRequiredIndicatorVisible(true);
        businessName.setRequiredIndicatorVisible(true);

        emailFieldBusiness.setMinLength(5);
        emailFieldBusiness.setMinLength(5);

        FormLayout formLayout = new FormLayout();
        formLayout.add(emailFieldBusiness,passwordFieldBusiness, passwordFieldRepeatBusiness , businessName);

        this.add(formLayout);
    }

    public BusinessDTOImpl createNewBusiness() {

        BusinessDTOImpl businessDTO = new BusinessDTOImpl();

        businessDTO.setBusinessName(businessName.getValue());
        businessDTO.setPassword(passwordFieldBusiness.getValue());
        businessDTO.setEmail(emailFieldBusiness.getValue());

        return businessDTO;
    }

    }



    public RegistrationView() {

        verticalLayout = new VerticalLayout();
        tabsLayout = new VerticalLayout();
        H1 h1 = new H1("Registrierung");

        StudentForm studentForm = new StudentForm();
        BusinessForm businessForm = new BusinessForm();

        verticalLayout.add(h1);

        registerButtonStudent = new Button("Registrieren");
        registerButtonStudent.addClickListener(e -> {
            StudentDTOImpl studentDTO = studentForm.createNewStudentDTO();
            RegistrationStudentDTOImpl registrationStudent = new RegistrationStudentDTOImpl(studentDTO , passwordFieldRepeatStudent.getValue());
            RegistrationResultDTO registrationResult = registrationControl.registerStudent(registrationStudent);

            if(registrationResult.getRegistrationResult()) {
                Utils.triggerDialogMessage("Registrierung erfolgreich" , "Weiterleitung per login wenn implementiert");
            } else {
                setStudentErrorFields(registrationResult.getReasons());
            }

        });

        registerButtonBusiness = new Button("Registrieren");
        registerButtonBusiness.addClickListener( e -> {


            BusinessDTOImpl businessDTO = businessForm.createNewBusiness();
            RegistrationBusinessDTOImpl registrationBusinessDTO = new RegistrationBusinessDTOImpl(businessDTO , passwordFieldRepeatBusiness.getValue());
            RegistrationResultDTO registrationResultDTO = registrationControl.registerBusiness(registrationBusinessDTO);
            registrationControl.registerBusiness(registrationBusinessDTO);

            if(registrationResultDTO.getRegistrationResult()) {
                Utils.triggerDialogMessage("Registrierung erfolgreich" , "Weiterleitung per login wenn implementiert");
            } else {
                Utils.triggerDialogMessage("Registrierung fehlgeschlagen" , registrationResultDTO.getReasons().toString());
            }

        });

        student = new Tab("Student");
        business = new Tab("Business");
        Tabs tabs = new Tabs(student,business);

        tabs.addSelectedChangeListener(event ->
             setContent(event.getSelectedTab())
        );

        setContent(tabs.getSelectedTab());
        verticalLayout.add(h1);
        add(verticalLayout);
        add(tabs,tabsLayout);

    }

    private void setContent(Tab tab) {

        tabsLayout.removeAll();

        if(tab.equals(student)) {
            tabsLayout.add(new StudentForm());
            tabsLayout.add(registerButtonStudent);

        }
        else {
            tabsLayout.add(new BusinessForm());
            tabsLayout.add(registerButtonBusiness);

        }

    }

    private void setStudentErrorFields(List<RegistrationResultDTO.RegistrationResultType> reasons) {
        for(RegistrationResultDTO.RegistrationResultType registrationResultType : reasons) {
            switch(registrationResultType) {
                case EMAIL_IN_USE:
                    emailFieldStudent.setErrorMessage("Diese E-Mail wird bereits verwendet");
                    emailFieldStudent.setInvalid(true);
                    break;
                case INVALID_EMAIL:
                    emailFieldStudent.setErrorMessage("Das Format der Email ist nicht gültig ");
                    emailFieldStudent.setInvalid(true);
                    break;
                case PASSWORD_TO_SHORT:
                    passwordFieldStudent.setErrorMessage("Das Password muss mindestens 5 Zeichen sein");
                    passwordFieldStudent.setInvalid(true);
                    break;
                case PASSWORD_REPEAT_TO_SHORT:
                    passwordFieldRepeatStudent.setErrorMessage("Das Password muss mindestens 5 Zeichen sein");
                    passwordFieldRepeatStudent.setInvalid(true);
                    break;
                case PASSWORD_DIFFERENT:
                    passwordFieldStudent.setErrorMessage("Unterschiedliches Password");
                    passwordFieldStudent.setInvalid(true);
                    passwordFieldRepeatStudent.setInvalid(true);
                    break;
                case INVALID_FIRST_NAME:
                    firstNameStudent.setErrorMessage("Ungültiger Vorname");
                    firstNameStudent.setInvalid(true);
                    break;
                case INVALID_LAST_NAME:
                    lastNameStudent.setErrorMessage("Ungültiger Nachname");
                    lastNameStudent.setInvalid(true);
                    break;
                default:
                    break;

            }
        }


    }


}
