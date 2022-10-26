package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("user/student")
@PageTitle("User")
public class StudentUserView extends VerticalLayout //implements View
{
    //GeneralUser attributes

    private final FormLayout formLayout;
    EmailField emailField = new EmailField("Email");
    private final TextField plz = new TextField("PLZ");
    private final TextField city = new TextField("City");
    private final TextField state = new TextField("State");
    private final TextField street = new TextField("Street");
    private final TextField streetNr = new TextField("StreetNr");

    //Student attributes
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final TextField faculty = new TextField("Faculty");
    //   private TextField birthDate = new TextField("Date of Birth");
    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    public StudentUserView() {
        addClassName("userView");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setMargin(true);
        //setJustifyContentMode(JustifyContentMode.CENTER);

        H3 title = new H3("Signup form");

        cancel.addClickListener(e -> {
            clearForm();
            Notification.show("User form cleared.");
        });

        save.addClickListener(e -> {
            // Speicherung der Daten über das zuhörige Control-Object.
            // Daten des Autos werden aus Formular erfasst und als DTO übergeben.
            // Zusätzlich wird das aktuelle StudentDTO übergeben.
            //GeneralUserDTO userDTO = (GeneralUserDTO) UI.getCurrent().getSession().getAttribute("user");
            //StudentDTO studentUserDTO = (StudentDTO) UI.getCurrent().getSession().getAttribute("student");
            //studentService.createStudent(binder.getBean() ,  studentDTO );

            Notification.show("User details stored.");
            //redirect to login page
            save.getUI().ifPresent(ui ->
                    ui.navigate(""));
        });

        formLayout = new FormLayout(title, emailField, firstName, lastName, plz, city, street, streetNr, state,
                faculty, cancel, save);

        // Restrict maximum width and center on page
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");
        add(new H1("User form"), formLayout);
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

    //private void showSuccess(Student detailsBean) {
        //Notification notification = Notification.show("Data saved, welcome " + detailsBean.getFirstName());
        //notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        // Here you'd typically redirect the user to another view
    //}


}
