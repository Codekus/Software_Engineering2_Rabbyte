package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.util.Utils;

public class MessageView extends VerticalLayout implements AfterNavigationObserver {

    public NumberField recieverId;

    int id;

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        id = Utils.getCurrentPerson().getId();
    }

    public MessageView() {
        NumberField recieverField = new NumberField();
        TextField messageText = new TextField();
        TextField title = new TextField();



    }

}
