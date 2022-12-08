package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.service.CrmService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.icon.Icon;


@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("Ihr Unternehmen")
@CssImport("./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
public class BusinessView extends VerticalLayout {


    @Autowired
    SecurityService securityService;
    Grid<JobAdvertisement> grid = new Grid<>(JobAdvertisement.class);
    private CrmService service;
    Button button;

    JobAdvertisementRepository jobAdvertisementRepository;


    public BusinessView(CrmService service, JobAdvertisementRepository jobAdvertisementRepository){
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.service = service;
        addClassName("job-advertisement-search-view");
        setSizeFull();
        add(showJobAd());

        button.addClickListener(e -> {
            upload();
            /* Just for test reasons
            Notification.show("clicked!");

             */
        });
    }

    private void upload(){
        remove(grid);
        configureGrid();
        add(grid);
        int i = securityService.getAuthenticatedUserID();
        grid.setItems(service.findJobAdvertisementByBusId(i));
    }

    private void configureGrid() {
        grid.addClassName("job-advertisement-grid");
        grid.setSizeFull();
        grid.removeAllColumns();

        //creating and styling the grid columns
        Grid.Column<JobAdvertisement> titleColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.title]]</a>")
                .withProperty("title", jobAdvertisement -> jobAdvertisement.getTitle())
        ).setHeader("Titel");
        Grid.Column<JobAdvertisement> descriptionColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.description]]</a>")
                .withProperty("description", JobAdvertisement::getText)
        ).setHeader("Beschreibung");
        Grid.Column<JobAdvertisement> typeColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("[[item.type]]")
                .withProperty("type", JobAdvertisement::getType)
        ).setHeader("Art");

        grid.addComponentColumn(jobAdvertisement -> new Button("Bearbeiten", click -> {
            UI.getCurrent().getSession().setAttribute("EditJobad", jobAdvertisement);
            NavigationUtil.toJobAdvertEditView();

        })).setKey("EditBtn");

        grid.addComponentColumn(jobAdvertisement -> new Button("Löschen", click -> {
                        Notification.show("Stellenausschreibung wurde gelöscht");
                        jobAdvertisementRepository.deleteJob(jobAdvertisement.getId());
                        upload();
        })).setKey("DeleteBtn");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        descriptionColumn.setAutoWidth(false);
    }


    private Component showJobAd(){
        FormLayout layout = new FormLayout();
        button = new Button("Ihre Ausschreibungen");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(button);
        return layout;
    }


}
