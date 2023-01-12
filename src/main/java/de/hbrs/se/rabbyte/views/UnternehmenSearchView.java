package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import com.vaadin.flow.component.textfield.TextField;
import de.hbrs.se.rabbyte.service.CrmService;


import org.springframework.context.annotation.Scope;

import java.util.List;

@org.springframework.stereotype.Component
@Scope("prototype")
//routing not final
@Route("Unternehmenssuche")
@PageTitle("Unternehmenssuche")
//@CssImport("./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
//@CssImport(themeFor = "vaadin-grid",value="./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
public class UnternehmenSearchView extends VerticalLayout {
    Grid<JobAdvertisement> grid = new Grid<>(JobAdvertisement.class);
    TextField searchField = new TextField();
    H1 infoMessage = new H1();
    private final Grid<List<String>> listGrid = new Grid<>();


    private CrmService service;

    public UnternehmenSearchView(CrmService service){
        this.service = service;
        addClassName("job-advertisement-search-view");
        setSizeFull();

        configureGrid();
        addClassName("job-advertisement-search-view-searchFieldComp");

        searchField.setPlaceholder("Nach Unternehmen suchen...");

        searchField.setClearButtonVisible(true);

        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        searchField.addValueChangeListener(e -> updateList());

        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());

        searchField.setMinWidth("20vW");
        add(this.createTitle(),
                searchField
        );
        updateList();

    }

    //update grid with search field input
    private void updateList() {
        if (!searchField.isEmpty()) {
            if (!service.findJobAdvertisements(searchField.getValue()).isEmpty()) {
                remove(infoMessage);
                add(grid);
                grid.setItems(service.findJobAdvertisements(searchField.getValue()));
            } else {
                noResults();
                remove(grid);
                add(infoMessage);
            }
        } else {
            remove(infoMessage);
            remove(grid);
        }
    }

    //grid creation
    private void configureGrid() {
        grid.addClassName("job-advertisement-grid");

        grid.setSizeFull();

        grid.removeAllColumns();

        //creating and styling the grid columns
        Grid.Column<JobAdvertisement> businessColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.business]]</a>")
                .withProperty("business", jobAdvertisement -> jobAdvertisement.getBusiness().getBusinessName())
        ).setHeader("Unternehmen");
        Grid.Column<JobAdvertisement> titleColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.title]]</a>")
                .withProperty("title", jobAdvertisement -> jobAdvertisement.getBusiness().getEmail())
        ).setHeader("E-Mail");
        Grid.Column<JobAdvertisement> descriptionColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.description]]</a>")
                .withProperty("description", jobAdvertisement -> jobAdvertisement.getBusiness().getId())
        ).setHeader("FirmenID");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        //added itemListener
        grid.addItemClickListener(e -> {
            if (e.getColumn().equals(businessColumn)) {

                findPath(e.getItem().getBusiness());

            }
        });
    }

    //routing to grid items
    private <T> void findPath(T idType) {
        if (idType instanceof Business) {
            //getUI().get().navigate("create_JobAdvert" +  ((Business) idType).getId());
            Notification.show(((Business) idType).getId() + " Business");
        } else if (idType instanceof JobAdvertisement) {
            //getUI().get().navigate("create_JobAdvert" +  ((JobAdvertisement) idType).getId());
            Notification.show(((JobAdvertisement) idType).getId() + " Job Advert");
        }
    }

    //create Rabbyte header
    private Component createTitle() {
        return new H1("Unternehmenssuche");
    }

    //set infoMessage (no results)
    private void noResults() {
        infoMessage.setText("Keine Übereinstimmungen!");
    }
}