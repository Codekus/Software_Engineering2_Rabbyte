package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.UI;

public class NavigationUtil {


    public static void toMainView() {
        UI.getCurrent().navigate("");
    }
}
