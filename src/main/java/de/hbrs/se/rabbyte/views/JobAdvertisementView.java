package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import java.awt.*;
import java.util.NoSuchElementException;

@org.springframework.stereotype.Component
@Scope("prototype")
@Route(value="jobAdvertisement")
@PageTitle("Stellenausschreibung")
@CssImport("./styles/views/JobAdvertisementView/job-advertisement-view.css")
public class JobAdvertisementView extends VerticalLayout
        implements HasUrlParameter<Integer>{

    @Autowired
    SecurityService securityService;
    private final  CrmService service;
    private Integer jobAdvertID;

    @Override
    public void setParameter(BeforeEvent event,@OptionalParameter Integer parameter) {
        try {
            if ((parameter == null) || service.findJobAdvertisementById(parameter) == null) {
                getUI().get().navigate(JobAdvertisementSearchView.class);
            } else {
                this.jobAdvertID = parameter;
                this.add(createTitle(jobAdvertID), createDescription(jobAdvertID), createBusinessField(jobAdvertID));
            }
        } catch (NoSuchElementException ex) {
            if (parameter != null) {
                Notification.show(parameter + " does not exist.");
            } else {
                Notification.show("Invalid input.");
            }
            event.rerouteTo(JobAdvertisementSearchView.class);

        }
    }

    public JobAdvertisementView(CrmService service) {
        this.service = service;
        setSizeFull();
        addClassName("job-advertisement-view");
    }


    private Component createTitle(Integer param) {
        H1 title = new H1(service.findJobAdvertisementById(param).getTitle());
        title.addClassName("job-advertisement-view-title");
        return title;
    }

    private Component createDescription(Integer param) {


        H2 descriptionHeader = new H2("Beschreibung der Ausschreibungsstelle:");
        Paragraph descriptionParagraph = new Paragraph(service.findJobAdvertisementById(param).getText());

        VerticalLayout contentDescription = new  VerticalLayout(descriptionHeader,descriptionParagraph);
        contentDescription.addClassName("job-advertisement-view-description");
        return contentDescription;
    }

    private Component createBusinessField(Integer param) {
        H2 info = new H2("Kontaktinformationen:");
        Html name = new Html("<span><b><u>"+"Unternehmen:"+"</u></b> "+service.findJobAdvertisementById(param).getBusiness().getBusinessName()+"</br></span>");
        Html email = new Html("<span><b><u>"+"Kontakt-Email:"+"</u></b> "+service.findJobAdvertisementById(param).getBusiness().getEmail()+"</br></span>");
        H2 details= new H2("Details:");
        Html type = new Html("<span><b><u>"+"Art:"+"</u></b> "+service.findJobAdvertisementById(param).getType()+"</br></span>");
        //TODO if else role handling
        VerticalLayout contentBusinessField = new VerticalLayout(info, name, email, details, type );

        if (securityService.getAuthenticatedUserRole().equals("Student")) {
           contentBusinessField.add(createApplyButton(param));
        }

        contentBusinessField.addClassName("job-advertisement-view-businessField");
        return  contentBusinessField;

    }
    private Component createApplyButton(Integer param){
        Button applyButton = new Button("Bewerben", click -> {
            });
        applyButton.addClassName("job-advertisement-view-applyButton");
        return applyButton;
    }


}
