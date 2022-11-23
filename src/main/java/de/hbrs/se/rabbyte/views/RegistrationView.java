package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.control.RegistrationControl;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.implemented.*;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = Globals.Path.REGISTRATION_VIEW)
@PageTitle(Globals.PageTitle.REGISTRATION_VIEW)
@Theme(value = Lumo.class)
public class RegistrationView extends VerticalLayout {

    @Autowired
    private RegistrationControl registrationControl;

    @Autowired
    private BusinessRepository businessRepository;

    //Student Fields
    EmailField emailFieldStudent = new EmailField("E-Mail");
    PasswordField passwordFieldStudent = new PasswordField("Password");
    PasswordField passwordFieldRepeatStudent = new PasswordField("Password wiederholen");
    TextField firstNameStudent = new TextField("Vorname");
    TextField lastNameStudent = new TextField("Nachname");
    ComboBox<String> facultyComboBox = new ComboBox<>("Fachbereich");

    //Business Fields
    EmailField emailFieldBusiness = new EmailField("E-Mail");
    PasswordField passwordFieldBusiness = new PasswordField("Password");
    PasswordField passwordFieldRepeatBusiness = new PasswordField("Password wiederholen");
    TextField businessNameField = new TextField("Business");

    //Tabs
    private final Tab studentTab;
    private final Tab businessTab;

    //Buttons
    Button registerButtonStudent;
    Button registerButtonBusiness;

    //Layouts
    VerticalLayout verticalLayout;
    VerticalLayout tabsLayout;

    //Tabs
    private String studentTabName = "Student";
    private String businessTabName = "Business";
    private String buttonRegistration = "Registrieren";


    class StudentForm extends Div {

        StudentForm() {

            emailFieldStudent.setRequiredIndicatorVisible(true);
            passwordFieldStudent.setRequiredIndicatorVisible(true);
            passwordFieldRepeatStudent.setRequiredIndicatorVisible(true);
            firstNameStudent.setRequiredIndicatorVisible(true);
            lastNameStudent.setRequiredIndicatorVisible(true);

            passwordFieldStudent.setMinLength(8);
            passwordFieldRepeatStudent.setMinLength(8);

            facultyComboBox = Globals.facultyComboBox(facultyComboBox);

            FormLayout formLayout = new FormLayout();
            formLayout.add(firstNameStudent, lastNameStudent, passwordFieldStudent, passwordFieldRepeatStudent,
                    facultyComboBox , emailFieldStudent);
            formLayout.setColspan(facultyComboBox , 2);
            formLayout.setColspan(emailFieldStudent , 2);
            setSizeFull();
            setAlignItems(Alignment.CENTER);
            this.add(formLayout);
        }

        public StudentDTOImpl createNewStudentDTO() {
            StudentDTOImpl newStudent = new StudentDTOImpl();
            newStudent.setEmail(emailFieldStudent.getValue());
            newStudent.setPassword(passwordFieldStudent.getValue());
            newStudent.setFirstName(firstNameStudent.getValue());
            newStudent.setLastName(lastNameStudent.getValue());
            newStudent.setFaculty(facultyComboBox.getValue());
            return newStudent;
        }
    }

    class BusinessForm extends Div {


        BusinessForm() {

            setAlignItems(Alignment.CENTER);
            emailFieldBusiness.setRequiredIndicatorVisible(true);
            passwordFieldBusiness.setRequiredIndicatorVisible(true);
            passwordFieldRepeatBusiness.setRequiredIndicatorVisible(true);
            businessNameField.setRequiredIndicatorVisible(true);

            emailFieldBusiness.setMinLength(8);
            emailFieldBusiness.setMinLength(8);

            FormLayout formLayout = new FormLayout();
            formLayout.add(businessNameField, passwordFieldBusiness, passwordFieldRepeatBusiness, emailFieldBusiness);

            formLayout.setColspan(businessNameField , 2);
            formLayout.setColspan(emailFieldBusiness , 2);
            setSizeFull();
            this.add(formLayout);
        }

        public BusinessDTOImpl createNewBusiness() {

            BusinessDTOImpl businessDTO = new BusinessDTOImpl();

            businessDTO.setBusinessName(businessNameField.getValue());
            businessDTO.setPassword(passwordFieldBusiness.getValue());
            businessDTO.setEmail(emailFieldBusiness.getValue());

            return businessDTO;
        }

    }


    public RegistrationView() {


        setAlignItems(Alignment.CENTER);

        verticalLayout = new VerticalLayout();
        tabsLayout = new VerticalLayout();

        H1 h1 = new H1("Registrierung");
        verticalLayout.add(h1);

        StudentForm studentForm = new StudentForm();
        BusinessForm businessForm = new BusinessForm();

        registerButtonStudent = new Button(buttonRegistration);
        registerButtonStudent.addClickListener(e -> {
            StudentDTOImpl studentDTO = studentForm.createNewStudentDTO();
            RegistrationStudentDTOImpl registrationStudent = new RegistrationStudentDTOImpl(studentDTO, passwordFieldRepeatStudent.getValue());
            RegistrationResultDTO registrationResult = registrationControl.registerStudent(registrationStudent);

            if (registrationResult.getRegistrationResult()) {
                successfulRegistration();
            } else {
                setStudentErrorFields(registrationResult.getReasons());
            }


        });

        registerButtonBusiness = new Button(buttonRegistration);
        registerButtonBusiness.addClickListener(e -> {

            BusinessDTOImpl businessDTO = businessForm.createNewBusiness();
            RegistrationBusinessDTOImpl registrationBusinessDTO = new RegistrationBusinessDTOImpl(businessDTO, passwordFieldRepeatBusiness.getValue());
            RegistrationResultDTO registrationResultDTO = registrationControl.registerBusiness(registrationBusinessDTO);
            registrationControl.registerBusiness(registrationBusinessDTO);

            if (registrationResultDTO.getRegistrationResult()) {
                successfulRegistration();
            } else {
                setBusinessErrorFields(registrationResultDTO.getReasons());

            }

        });

        this.studentTab = new Tab(studentTabName);
        this.businessTab = new Tab(businessTabName);

        Tabs tabs = new Tabs(this.studentTab, businessTab);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );

        setContent(tabs.getSelectedTab());
        verticalLayout.setWidth("50%");
        verticalLayout.add(h1);
        verticalLayout.add(tabs , tabsLayout);
        add(verticalLayout);

    }

    private static void successfulRegistration() {
        Utils.triggerDialogMessage("Registrierung erfolgreich", "Um ihren Account zu aktivieren, bitte klicken sie auf den Link in der E-Mail");
        NavigationUtil.toLoginView();
    }

    private void setContent(Tab tab) {

        tabsLayout.removeAll();

        if (tab.equals(studentTab)) {
            tabsLayout.add(new StudentForm());
            tabsLayout.add(registerButtonStudent);

        } else {
            tabsLayout.add(new BusinessForm());
            tabsLayout.add(registerButtonBusiness);

        }

    }

    private void setStudentErrorFields(List<RegistrationResultDTO.Result> reasons) {
        for (RegistrationResultDTO.Result result : reasons) {
            switch (result) {
                case EMAIL_IN_USE:
                    emailFieldStudent.setErrorMessage(Globals.FieldErrorMessages.EMAIL_IN_USE);
                    emailFieldStudent.setInvalid(true);
                    break;
                case INVALID_EMAIL:
                    emailFieldStudent.setErrorMessage(Globals.FieldErrorMessages.INVALID_EMAIL);
                    emailFieldStudent.setInvalid(true);
                    break;
                case PASSWORD_TO_SHORT:
                    passwordFieldStudent.setErrorMessage(Globals.FieldErrorMessages.PASSWORD_TOO_SHORT);
                    passwordFieldStudent.setInvalid(true);
                    break;
                case PASSWORD_REPEAT_TO_SHORT:
                    passwordFieldRepeatStudent.setErrorMessage(Globals.FieldErrorMessages.PASSWORD_TOO_SHORT);
                    passwordFieldRepeatStudent.setInvalid(true);
                    break;
                case PASSWORD_DIFFERENT:
                    passwordFieldStudent.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_DIFFERENT);
                    passwordFieldStudent.setInvalid(true);
                    passwordFieldRepeatStudent.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_DIFFERENT);
                    passwordFieldRepeatStudent.setInvalid(true);
                    break;
                case INVALID_FIRST_NAME:
                    firstNameStudent.setErrorMessage(Globals.FieldErrorMessages.INVALID_FIRST_NAME);
                    firstNameStudent.setInvalid(true);
                    break;
                case INVALID_LAST_NAME:
                    lastNameStudent.setErrorMessage(Globals.FieldErrorMessages.INVALID_LAST_NAME);
                    lastNameStudent.setInvalid(true);
                    break;
                case PASSWORD_TOO_COMMON:
                    passwordFieldStudent.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_TOO_COMMON);
                    passwordFieldStudent.setInvalid(true);
                    passwordFieldRepeatStudent.setInvalid(true);
                    passwordFieldRepeatStudent.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_TOO_COMMON);
                    break;
                default:
                    break;

            }
        }
    }

    private void setBusinessErrorFields(List<RegistrationResultDTO.Result> reasons) {
        for (RegistrationResultDTO.Result result : reasons) {
            switch (result) {
                case EMAIL_IN_USE:
                    emailFieldBusiness.setErrorMessage(Globals.FieldErrorMessages.EMAIL_IN_USE);
                    emailFieldBusiness.setInvalid(true);
                    break;
                case INVALID_EMAIL:
                    emailFieldBusiness.setErrorMessage(Globals.FieldErrorMessages.INVALID_EMAIL);
                    emailFieldBusiness.setInvalid(true);
                    break;
                case PASSWORD_TO_SHORT:
                    passwordFieldBusiness.setErrorMessage(Globals.FieldErrorMessages.PASSWORD_TOO_SHORT);
                    passwordFieldBusiness.setInvalid(true);
                    break;
                case PASSWORD_REPEAT_TO_SHORT:
                    passwordFieldRepeatBusiness.setErrorMessage(Globals.FieldErrorMessages.PASSWORD_TOO_SHORT);
                    passwordFieldRepeatBusiness.setInvalid(true);
                    break;
                case PASSWORD_DIFFERENT:
                    passwordFieldBusiness.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_DIFFERENT);
                    passwordFieldBusiness.setInvalid(true);
                    passwordFieldRepeatBusiness.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_TOO_COMMON);
                    passwordFieldRepeatBusiness.setInvalid(true);
                    break;
                case INVALID_BUSINESS_NAME:
                    businessNameField.setErrorMessage(Globals.FieldErrorMessages.BUSINESS_NAME);
                    businessNameField.setInvalid(true);
                    break;
                case BUSINESS_NAME_IN_USE:
                    businessNameField.setErrorMessage(Globals.FieldErrorMessages.BUSINESS_NAME_IN_USE);
                    businessNameField.setInvalid(true);
                    break;
                case PASSWORD_TOO_COMMON:

                    passwordFieldBusiness.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_TOO_COMMON);
                    passwordFieldBusiness.setInvalid(true);
                    passwordFieldRepeatBusiness.setInvalid(true);
                    passwordFieldRepeatBusiness.setErrorMessage(Globals.FieldErrorMessages.MEMORIZED_SECRET_TOO_COMMON);
                    break;
                default:
                    break;

            }
        }
    }

}
