package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.dtos.*;

import de.hbrs.se.rabbyte.security.SecurityService;

import java.util.Optional;

import de.hbrs.se.rabbyte.util.NavigationUtil;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * The main view is a top-level placeholder for other views.
 */
@Scope("prototype")
@org.springframework.stereotype.Service
//@PWA(name = "HelloCar", shortName = "HelloCar", enableInstallPrompt = false) TODO: whats this??
public class AppView extends AppLayout implements BeforeEnterObserver {

    private Tabs menu;
    //private H3 viewTitle;
    private H1 helloUser;

    @Autowired
    SecurityService securityService;



    public AppView() {
        //this.securityService = securityService;


    }

    @PostConstruct
    public void setUpUI() {
        // Anzeige des Toggles über den Drawer
        setPrimarySection(Section.DRAWER);

        // Erstellung der horizontalen Statusleiste (Header)
        addToNavbar(true, createHeaderContent());

        // Erstellung der vertikalen Navigationsleiste (Drawer)


        //menu = createMenu();
        //addToDrawer(createDrawerContent(menu));
    }




    /**
     * Erzeugung der horizontalen Leiste (Header).
     * @return
     */
    private Component createHeaderContent() {
        // Ein paar Grund-Einstellungen. Alles wird in ein horizontales Layout gesteckt.
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode( FlexComponent.JustifyContentMode.EVENLY );

        // Hinzufügen des Toogle ('Big Mac') zum Ein- und Ausschalten des Drawers
        DrawerToggle drawerToggle = new DrawerToggle();
        setDrawerOpened(false);
        //layout.add(drawerToggle);

        HorizontalLayout hlogoLayout = new HorizontalLayout();

        // Hinzufügen des Logos
        Image logo = new Image("images/rabbit_logo.png" , "logoImage");
        logo.setWidth("50px");
        hlogoLayout.setId("logo");
        hlogoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        hlogoLayout.add(logo);
        hlogoLayout.add(new H1("Rabbyte"));
        VerticalLayout vLogoLayout = new VerticalLayout(hlogoLayout);
        layout.add(vLogoLayout);

        /*
        viewTitle = new H3();
        viewTitle.setWidthFull();
        layout.add( viewTitle );
         */
        menu = createMenu();
        VerticalLayout vMenu = new VerticalLayout(menu);
        layout.add(vMenu);

        // Interner Layout
        HorizontalLayout topRightPanel = new HorizontalLayout();
        topRightPanel.setWidthFull();
        topRightPanel.setJustifyContentMode( FlexComponent.JustifyContentMode.END );
        topRightPanel.setAlignItems( FlexComponent.Alignment.CENTER );

        //Message Button

        // Logout-Button am rechts-oberen Rand.
        MenuBar bar = new MenuBar();
        topRightPanel.add(bar);

        MenuItem move = bar.addItem("Mein Profil");

        SubMenu moveSubMenu = move.getSubMenu();

        moveSubMenu.addItem("Logout", e -> securityService.logout());
        moveSubMenu.addItem("Dark/Light-Mode", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        }).addComponentAsFirst(new Icon(VaadinIcon.MOON));

        //moveSubMenu.addItem("Einstellungen",  e -> securityService.settings());
        moveSubMenu.addItem("Logout",  e -> securityService.logout());
        moveSubMenu.addItem("Messages" , (e -> NavigationUtil.toMessageView()));
        layout.add(topRightPanel);
        layout.setMaxHeight("80px");
        return layout;
    }

    /**
     * Hinzufügen der vertikalen Leiste (Drawer)
     * Diese besteht aus dem Logo ganz oben links sowie den Menu-Einträgen (menu items).
     * Die Menu Items sind zudem verlinkt zu den internen Tab-Components.
     * @param menu
     * @return
     */
    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        HorizontalLayout logoLayout = new HorizontalLayout();

        // Hinzufügen des Logos
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "Logo"));
        logoLayout.add(new H1("Logo"));

        // Hinzufügen des Menus inklusive der Tabs
        layout.add(logoLayout, menu);
        return layout;
    }


    /**
     * Erzeugung des Menu auf der vertikalen Leiste (Drawer)
     * @return
     */

    private Tabs createMenu() {

        // Anlegen der Grundstruktur
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.addThemeVariants(TabsVariant.LUMO_HIDE_SCROLL_BUTTONS);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.setId("tabs");
        tabs.setMaxWidth("100%");
        tabs.setWidth("800px");

        // Anlegen der einzelnen Menuitems
        tabs.add(createMenuItems());

        return tabs;
    }



    private Component[] createMenuItems() {
        // Abholung der Referenz auf den Authorisierungs-Service
        //authorizationControl = new AuthorizationControl();

        // Jeder User sollte Autos sehen können, von daher wird dieser schon mal erzeugt und
        // und dem Tabs-Array hinzugefügt. In der Methode createTab wird ein (Key, Value)-Pair übergeben:
        // Key: der sichtbare String des Menu-Items
        // Value: Die UI-Component, die nach dem Klick auf das Menuitem angezeigt wird.
        //ToDo Sicherstellen dass es sich um ein Unternehmens account handelt


        // Falls er Admin-Rechte hat, sollte der User auch Autos hinzufügen können
        // (Alternative: Verwendung der Methode 'isUserisAllowedToAccessThisFeature')

        /*
        if ( this.authorizationControl.isUserInRole( this.getCurrentUser() , Globals.Roles.ADMIN ) ) {
            System.out.println("User is Admin!");
            tabs = Utils.append( tabs , createTab("Enter Car", EnterCarView.class)  );
        }
         */
        Tab[] tabs = new Tab[0];
        if (securityService.getAuthenticatedUserRole().equals("Student")) {
            tabs = new Tab[]{ createTab( "Startseite", JobAdvertisementSearchView.class) };
            tabs = Utils.append( tabs , createTab("Kontodaten ändern", StudentUserView.class));
            tabs = Utils.append( tabs , createTab("Unternehmen suchen", UnternehmenSearchView.class));
            tabs = Utils.append( tabs , createTab("Global Chat", ChatView.class));
        } else if ( securityService.getAuthenticatedUserRole().equals("Business") ) {
            tabs = new Tab[]{ createTab( "Ihr Unternehmen", BusinessView.class) };
            tabs = Utils.append( tabs , createTab("Neue Stellenanzeige", CreateJobAdvertisementView.class));
        }

        return tabs;
    }



    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }




    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }



    @Override
    /**
     * Methode wird vor der eigentlichen Darstellung der UI-Components aufgerufen.
     * Hier kann man die finale Darstellung noch abbrechen, wenn z.B. der Nutzer nicht eingeloggt ist
     * Dann erfolgt hier ein ReDirect auf die Login-Seite. Eine Navigation (Methode navigate)
     * ist hier nicht möglich, da die finale Navigation noch nicht stattgefunden hat.
     * Diese Methode in der AppLayout sichert auch den un-authorisierten Zugriff auf die innerliegenden
     * Views (hier: ShowCarsView und EnterCarView) ab.
     *
     */

    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
/*
        if (!isUserLoggedIn()){
            beforeEnterEvent.rerouteTo("login");
        }

 */



    }
}
