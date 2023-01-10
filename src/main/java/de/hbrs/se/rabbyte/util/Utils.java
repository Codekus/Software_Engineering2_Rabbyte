package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.hbrs.se.rabbyte.dtos.PersonDTO;

import java.util.Arrays;

public class Utils {
    private Utils() {
        throw new IllegalStateException(Globals.IllegalState.MESSAGE_UTILS);
    }

    /**
     * Nützliche Methdode zur Erweiterung eines bestehendes Arrays
     * Oma hätte gesagt, so eine Methode 'fällt nicht durch' ;-)
     * <p>
     * https://stackoverflow.com/questions/2843366/how-to-add-new-elements-to-an-array
     */
    public static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }


    public static void triggerDialogMessage(String headerText, String message) {
        Dialog dialog = new Dialog();

        H3 header = new H3(headerText);
        Label contentText = new Label(message);
        Button ok = new Button("Ok");

        ok.addClickListener(e -> dialog.close());

        HorizontalLayout text = new HorizontalLayout(contentText);
        HorizontalLayout butt = new HorizontalLayout(ok);

        VerticalLayout dialogContent = new VerticalLayout(header, text, butt);
        dialogContent.setAlignItems(FlexComponent.Alignment.CENTER);
        dialog.add(dialogContent);
        dialog.open();
    }

    public static PersonDTO getCurrentPerson() {
       return (PersonDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }
}
