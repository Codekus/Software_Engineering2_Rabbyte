package de.hbrs.se.rabbyte.views;

import com.sun.xml.bind.v2.TODO;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import com.vaadin.flow.component.textfield.TextField;
import de.hbrs.se.rabbyte.service.CrmService;


import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Component
@Scope("prototype")
//routing not final
//@Route(value = "search2")
@PageTitle("Search For Job-Advertisements")
@CssImport("./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
//@CssImport(themeFor = "vaadin-grid",value="./styles/views/JobAdvertisementSearchView/job-advertisements-search-view.css")
public class JobAdvertisementSearchView extends VerticalLayout {
    private final CrmService service;

    Grid<JobAdvertisement> grid = new Grid<>(JobAdvertisement.class);

    TextField searchField = new TextField();

    H1 infoMessage = new H1();

    HashMap<String, String> extendedSearchList = new HashMap<>();

    HashMap<String,MenuItem> currentSelection = new HashMap<>();

    List<JobAdvertisement> gridSearchList = new ArrayList<>();

    List<String> searchInput = new ArrayList<>();



    public JobAdvertisementSearchView(CrmService service) {
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
        //TODO maybe split into new method
        searchInput = Arrays.asList((searchField.getValue()).split(" "));

        //create list of first keyword
        if (gridSearchList.isEmpty()) {
            gridSearchList = new ArrayList<>(service.findJobAdvertisements(searchInput.get(0)));
        }
        if (!searchField.isEmpty()) {


            if (!gridSearchList.isEmpty()) {
                remove(infoMessage);
                gridSearchList = service.filterJobAdvertisementsByKeywordList(gridSearchList, searchInput);

                //Apply selected filter
                if (extendedSearchList.get("Unternehmen") != null) {
                    gridSearchList = service.filterJobAdvertisementsByKeyword(gridSearchList, extendedSearchList.get("Unternehmen"), "Unternehmen");
                }
                if (extendedSearchList.get("Art") != null) {
                    gridSearchList = service.filterJobAdvertisementsByKeyword(gridSearchList, extendedSearchList.get("Art"), "Art");
                }

                add(grid);
                grid.setItems(gridSearchList);
            } else {
                noResults();
                remove(grid);
                add(infoMessage);
            }
        } else {
            gridSearchList.clear();
            remove(infoMessage);
            remove(grid);
        }
    }

    //create search field component
    private Component getSearchFieldComp() {
        HorizontalLayout searchComp = new HorizontalLayout();
        addClassName("job-advertisement-search-view-searchFieldComp");

        searchField.setPlaceholder("Nach Ausschreibungen suchen...");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setMinWidth("20vW");
        searchComp.add(searchField, createExtendedSearchComp());


        return searchComp;
    }

    private Component createExtendedSearchComp() {
        MenuBar menuBar = new MenuBar();
        menuBar.addClassName("job-advertisement-search-view-ExtendedSearchComp");

        //filterIcon
        Icon filterIcon = VaadinIcon.FILTER.create();
        filterIcon.getElement().getStyle().set("color", "black");


        //add menu
        MenuItem extendedSearch = menuBar.addItem(filterIcon);
        menuBar.getElement().setProperty("title","Filter");
        extendedSearch.getElement().addEventListener("mouseover", e -> e.getSource().getStyle().set("cursor", "pointer"));

        //add submenu
        SubMenu extendedSearchSubMenu = extendedSearch.getSubMenu();

        //add company names
        MenuItem chooseCompany = extendedSearchSubMenu.addItem("Unternehmen");
        SubMenu companySubMenu = chooseCompany.getSubMenu();
        List<String> businessNames = service.findAllBusinessNames();


        //add items with event listener for business
        for (String business : businessNames
        ) {
            companySubMenu.addItem(business, e -> {

                if ((e.getSource().getText().equals(extendedSearchList.get("Unternehmen")))) {
                    //unselect item
                    extendedSearchList.put("Unternehmen", null);
                    //turn black if no filter selected
                    if (extendedSearchList.get("Art") == null) {
                        filterIcon.setColor("black");
                    }
                    //reset grid pre filter
                    gridSearchList = new ArrayList<>(service.findJobAdvertisements(searchInput.get(0)));
                } else {
                    if(currentSelection.get("Unternehmen")!=null) {
                       currentSelection.get("Unternehmen").setChecked(false);
                    }
                    extendedSearchList.put("Unternehmen", e.getSource().getElement().getText());
                    e.getSource().isChecked();
                    currentSelection.put("Unternehmen",e.getSource());

                    //turn blue if any filter is selected
                    filterIcon.setColor("blue");
                }
                updateList();
            }).setCheckable(true);


        }

        //add jobadvert types
        MenuItem chooseType = extendedSearchSubMenu.addItem("Art");
        SubMenu typeSubMenu = chooseType.getSubMenu();
        List<String> jobAdvertTypes = service.getAllJobAdvertisementTypes();

        //add items with event listener for type
        for (String type : jobAdvertTypes
        ) {
            typeSubMenu.addItem(type, e -> {
                if (e.getSource().getText().equals(extendedSearchList.get("Art"))) {

                    //unselect item
                    extendedSearchList.put("Art", null);
                    //turn black if no filter selected
                    if (extendedSearchList.get("Unternehmen") == null) {
                        filterIcon.setColor("black");
                    }
                    //reset grid pre filter
                    gridSearchList = new ArrayList<>(service.findJobAdvertisements(searchInput.get(0)));
                } else {
                    if (extendedSearchList.get("Art") != null) {
                        currentSelection.get("Art").setChecked(false);
                    }

                    //add current selection to the Hashmap and extended
                    extendedSearchList.put("Art", e.getSource().getElement().getText());
                    e.getSource().isChecked();
                    currentSelection.put("Art",e.getSource());

                    //turn blue if any filter is selected
                    filterIcon.setColor("blue");

                }
                updateList();
            }).setCheckable(true);
        }

        return menuBar;
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
                .withProperty("title", JobAdvertisement::getTitle)
        ).setHeader("Title");
        Grid.Column<JobAdvertisement> descriptionColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("<a class=\"link-columns\">[[item.description]]</a>")
                .withProperty("description", JobAdvertisement::getText)
        ).setHeader("Beschreibung");
        Grid.Column<JobAdvertisement> typeColumn = grid.addColumn(TemplateRenderer
                .<JobAdvertisement>of("[[item.type]]")
                .withProperty("type", JobAdvertisement::getType)
        ).setHeader("Art");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        descriptionColumn.setAutoWidth(false);

        //added itemListener
        grid.addItemClickListener(e -> {
            if (e.getColumn().equals(businessColumn)) {
                findPath(e.getItem().getBusiness());

            } else if (e.getColumn().equals(titleColumn) || e.getColumn().equals(descriptionColumn)) {
                findPath(e.getItem());

            }
        });
    }

    //routing to grid items
    private <T> void findPath(T idType) {
        if (idType instanceof Business) {
            //getUI().get().navigate(BusinessProfilView.class, ((JobAdvertisement) idType).getId());

            Notification.show(((Business) idType).getId() + " Business");
        } else if (idType instanceof JobAdvertisement) {

            getUI().get().navigate(JobAdvertisementView.class, ((JobAdvertisement) idType).getId());

        }
    }

    //create Rabbyte header
    private Component createTitle() {
        return new H1("Rabbyte");
    }

    //set infoMessage (no results)
    private void noResults() {
        infoMessage.setText("Keine Übereinstimmungen!");
    }
}