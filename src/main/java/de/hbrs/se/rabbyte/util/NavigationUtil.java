package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.UI;

public class NavigationUtil {


    public static void toMainView() {
        UI.getCurrent().navigate("");
    }

    public static void toLoginView() {
        UI.getCurrent().navigate("login");
    }
}
