package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.UI;

public class NavigationUtil {

    private NavigationUtil() {
        throw new IllegalStateException(Globals.IllegalState.MESSAGE_UTILS);
    }

    public static void toMainView() {
        UI.getCurrent().navigate("");
    }

    public static void toLoginView() {
        UI.getCurrent().navigate("login");
    }

    public static void toRegisterView() {
        UI.getCurrent().navigate("registration");
    }

    public static void toMessageView() {
        UI.getCurrent().navigate("message");
    }

}
