package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import de.hbrs.se.rabbyte.dtos.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */

@Route("main")
//@PWA(name = "HelloCar", shortName = "HelloCar", enableInstallPrompt = false) TODO: whats this??
public class AppView extends AppLayout implements BeforeEnterObserver {

    private Tabs menu;
    private H1 viewTitle;
    private H1 helloUser;

    public AppView() {
        Integer x = 1;
        // getCurrentUser() todo
        if (x == null) {
            System.out.println("LOG: In Constructor of App View - No User given!");
        } else {
            setUpUI();
        }
    }

    public void setUpUI() {
        // Anzeige des Toggles über den Drawer
        setPrimarySection(Section.DRAWER);

        // Erstellung der horizontalen Statusleiste (Header)
        addToNavbar(true, createHeaderContent());

        // Erstellung der vertikalen Navigationsleiste (Drawer)


       // menu = createMenu();
       // addToDrawer(createDrawerContent(menu));
    }


    private boolean checkIfUserIsLoggedIn() {
        // Falls der Benutzer nicht eingeloggt ist, dann wird er auf die Startseite gelenkt

        /*
        UserDTO userDTO = this.getCurrentUser();
        if (userDTO == null) {
            UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
            return false;
        }
        return true;

         */
        return false;
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
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        viewTitle.setWidthFull();
        layout.add( viewTitle );

        // Interner Layout
        HorizontalLayout topRightPanel = new HorizontalLayout();
        topRightPanel.setWidthFull();
        topRightPanel.setJustifyContentMode( FlexComponent.JustifyContentMode.END );
        topRightPanel.setAlignItems( FlexComponent.Alignment.CENTER );

        // Logout-Button am rechts-oberen Rand.
        MenuBar bar = new MenuBar();
        topRightPanel.add(bar);

        MenuItem move = bar.addItem("Mein Profil");
        SubMenu moveSubMenu = move.getSubMenu();
        moveSubMenu.addItem("Logout",  e -> logoutUser());

        layout.add( topRightPanel );
        return layout;
    }

    private void logoutUser() {

        UI ui = this.getUI().get();
        ui.getPage().setLocation("/");
        ui.getSession().close();
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
        logoLayout.add(new Image("images/logo.png", "HelloCar logo"));
        logoLayout.add(new H1("HelloCar"));

        // Hinzufügen des Menus inklusive der Tabs
        layout.add(logoLayout, menu);
        return layout;
    }


    /**
     * Erzeugung des Menu auf der vertikalen Leiste (Drawer)
     * @return
     */
    /*
    private Tabs createMenu() {

        // Anlegen der Grundstruktur
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");

        // Anlegen der einzelnen Menuitems
        tabs.add(createMenuItems());
        return tabs;
    }
    */

    /*
    private Component[] createMenuItems() {
        // Abholung der Referenz auf den Authorisierungs-Service
        authorizationControl = new AuthorizationControl();

        // Jeder User sollte Autos sehen können, von daher wird dieser schon mal erzeugt und
        // und dem Tabs-Array hinzugefügt. In der Methode createTab wird ein (Key, Value)-Pair übergeben:
        // Key: der sichtbare String des Menu-Items
        // Value: Die UI-Component, die nach dem Klick auf das Menuitem angezeigt wird.
        Tab[] tabs = new Tab[]{ createTab( "Show Cars", ShowCarsView.class) };

        // Falls er Admin-Rechte hat, sollte der User auch Autos hinzufügen können
        // (Alternative: Verwendung der Methode 'isUserisAllowedToAccessThisFeature')
        if ( this.authorizationControl.isUserInRole( this.getCurrentUser() , Globals.Roles.ADMIN ) ) {
            System.out.println("User is Admin!");
            tabs = Utils.append( tabs , createTab("Enter Car", EnterCarView.class)  );
        }



        return tabs;
    }

     */

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

    /*
    private String getCurrentNameOfUser() {
        return getCurrentUser().getFirstName();
    }

     */


    private GeneralUserDTO getCurrentUser() {
        // TODO: Beim login muss der user als Attribute gesetzt werden
        return (GeneralUserDTO) UI.getCurrent().getSession().getAttribute("user"); // TODO globals erstellen
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

        // TODO: richtig implementieren
        /*
        if (getCurrentUser() == null){
            beforeEnterEvent.rerouteTo("login");    TODO: globals erstellen
        }

         */



    }
}
