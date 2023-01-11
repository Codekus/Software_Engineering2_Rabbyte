package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.service.CrmService;


import de.hbrs.se.rabbyte.util.NavigationUtil;
import org.springframework.context.annotation.Scope;

import java.util.List;

@org.springframework.stereotype.Component
@Scope("prototype")
//routing not final
@Route("studentensuche")
@PageTitle("Studentensuche")
//@CssImport("./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
//@CssImport(themeFor = "vaadin-grid",value="./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
public class SearchStudentsView extends VerticalLayout {
    Grid<Student> grid = new Grid<>(Student.class);
    TextField searchField = new TextField();
    H1 infoMessage = new H1();
    private final Grid<List<String>> listGrid = new Grid<>();


    private CrmService service;

    public SearchStudentsView(CrmService service){
        this.service = service;
        addClassName("job-advertisement-search-view");
        setSizeFull();

        configureGrid();
        add(this.createTitle(),
                getSearchFieldComp()
        );
        updateList();
    }

    //update grid with search field input
    private void updateList() {
        if (!searchField.isEmpty()) {
            if (!service.findStudentsByStrings(searchField.getValue()).isEmpty()) {
                remove(infoMessage);
                add(grid);
                grid.setItems(service.findStudentsByStrings(searchField.getValue()));
            } else {
                noResults();
                remove(grid);
                add(infoMessage);
            }
        } else {
            add(grid);
            grid.setItems(service.getAllStudents());
            remove(infoMessage);
        }
    }

    //create search field component
    private Component getSearchFieldComp() {
        addClassName("job-advertisement-search-view-searchFieldComp");
        searchField.setPlaceholder("Nach Studenten suchen...");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setMinWidth("20vW");

        return searchField;
    }

    //grid creation
    private void configureGrid() {
        grid.addClassName("job-advertisement-grid");
        grid.setSizeFull();
        grid.removeAllColumns();

        //creating and styling the grid columns
        Grid.Column<Student> firstnameColumn = grid.addColumn(TemplateRenderer
                .<Student>of("<a class=\"link-columns\">[[item.firstname]]</a>")
                .withProperty("firstname", Student::getFirstName)
        ).setHeader("Vorname");

        Grid.Column<Student> lastnameColumn = grid.addColumn(TemplateRenderer
                .<Student>of("<a class=\"link-columns\">[[item.lastname]]</a>")
                .withProperty("lastname", Student::getLastName)
        ).setHeader("Nachname");

        Grid.Column<Student> emailColumn = grid.addColumn(TemplateRenderer
                .<Student>of("[[item.email]]")
                .withProperty("email", Student::getEmail)
        ).setHeader("Email");


        Grid.Column<Student> cityColumn = grid.addColumn(TemplateRenderer
                .<Student>of("[[item.city]]")
                .withProperty("city", Student::getCity)
        ).setHeader("Stadt");

        Grid.Column<Student> facultyColumn = grid.addColumn(TemplateRenderer
                .<Student>of("[[item.faculty]]")
                .withProperty("faculty", Student::getFaculty)
        ).setHeader("Fakultät");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    //create Rabbyte header
    private Component createTitle() {
        return new H1("Studentensuche");
    }

    //set infoMessage (no results)
    private void noResults() {
        infoMessage.setText("Keine Übereinstimmungen!");
    }
}