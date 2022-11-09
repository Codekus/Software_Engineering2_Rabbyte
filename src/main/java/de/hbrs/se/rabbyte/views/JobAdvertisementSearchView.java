package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import com.vaadin.flow.component.textfield.TextField;
import de.hbrs.se.rabbyte.service.CrmService;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope("prototype")
//routing not final
//@Route(value = "search2")
@PageTitle("Search For Job-Advertisements")
@CssImport("./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
public class JobAdvertisementSearchView extends VerticalLayout {
    Grid<JobAdvertisement> grid = new Grid<>(JobAdvertisement.class);
    TextField searchField = new TextField();
    private CrmService service;


    public JobAdvertisementSearchView(CrmService service){
        this.service = service;
        addClassName("job-advertisement-search-view");
        setSizeFull();

        configureGrid();
        add(this.createTitle(),
                getSearchFieldComp()
        );
        updateList();

    }

    private void updateList() {
        if(!searchField.isEmpty()) {
            add(grid);
            grid.setItems(service.findJobAdvertisements(searchField.getValue()));
        }else {
            remove(grid);
        }
    }

    private Component getSearchFieldComp() {
        addClassName("job-advertisement-search-view-searchFieldComp");
        searchField.setPlaceholder("Nach Ausschreibungen suchen...");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setMinWidth("20vW");

        return searchField;
    }

    private void configureGrid(){
        grid.addClassName("job-advertisement-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(jobAdvertisement -> jobAdvertisement.getBusiness().getBusinessName()).setHeader("Unternehmen");
        grid.addColumn(jobAdvertisement -> jobAdvertisement.getTitle()).setHeader("Titel");
        grid.addColumn(jobAdvertisement -> jobAdvertisement.getText()).setHeader("Beschreibung");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }


    private Component createTitle() {
        return new H1("Rabbyte");
    }
}