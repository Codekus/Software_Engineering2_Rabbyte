package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.PWA;



//@Route(value = "main", layout = AppView.class)
@PWA(name = "Rabbyte", shortName = "Rabbyte")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me",
                event -> {
                    Notification.show("Clicked!");
                    UI.getCurrent().navigate("student/search-view");
                });

        add(button);
    }
}

