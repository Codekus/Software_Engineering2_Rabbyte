package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import de.hbrs.se.rabbyte.control.MessageControl;
import de.hbrs.se.rabbyte.control.RegistrationControl;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.util.Globals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageView extends Div implements BeforeEnterObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControl.class);

    public NumberField recieverId;
    int id;

    @Autowired
    MessageControl messageControl;

    private Grid<MessageDTO> grid;
    private List<MessageDTO> messages = new ArrayList<>();
    private Div hint;
    private SplitLayout splitLayout = new SplitLayout();
    TextField receiver;
    TextField title ;
    TextField messageText ;

    public MessageView() {
        //Vaadin required
    }

    public MessageDTOImpl createMessageDTO() {
        MessageDTOImpl messageDTO = new MessageDTOImpl();
        messageDTO.setMessageText(messageText.getValue());
        messageDTO.setTitle(title.getValue());
        messageDTO.setReceiver(Integer.parseInt(receiver.getValue()));
        return messageDTO;
    }

    private void refreshGrid() {
        if (!(messages.isEmpty())) {
            grid.setVisible(true);
            hint.setVisible(false);
            grid.getDataProvider().refreshAll();
        } else {
            grid.setVisible(false);
            hint.setVisible(true);
        }
    }

    private void setupGrid() {

        String person = messageControl.getCurrentUser();
        int id = this.messageControl.getId(person);


        grid = new Grid<>(MessageDTO.class, false);
        grid.setVisible(true);

        // Read/Not read
        grid.addComponentColumn(message -> {
            if (!message.getRead())
                return VaadinIcon.ENVELOPE.create();
            else
                return VaadinIcon.CHECK.create();
        }).setHeader(" ").setFlexGrow(0).setWidth("50px");

        // Resolution of Sender via control class
        grid.addColumn(message -> {
            try {
                return messageControl.getPersonName(message.getSender());
            } catch (DatabaseUserException e) {
                e.printStackTrace();
            }
            return null;
        }).setHeader("Absender").setSortable(true);

        // Resolution of Subject via control class
        grid.addColumn(message -> {
            try {
                return messageControl.getSubject(message.getId());
            } catch (DatabaseUserException e) {
                e.printStackTrace();
            }
            return null;
        }).setHeader("Betreff").setSortable(true);

        // Date of received message
        grid.addColumn(MessageDTO::getDate).setHeader("Datum").setSortable(true);

        // Clicking a message opens it in the lower part of the window
        grid.addItemClickListener(message -> {
            try {
                if(grid.getSelectionModel().getFirstSelectedItem().isPresent()) {
                    toggleReply(message.getItem(), true);
                    if(!message.getItem().getRead())
                        messageControl.setMessageAsRead(message.getItem());
                }
                else
                    toggleReply(null, false);

            } catch(DatabaseUserException e) {
                e.printStackTrace();
            }
        });

        this.messages = messageControl.getMessages(messageControl.getId(person));
        grid.setItems(messages);

        // Hint if user has no messages
        this.hint = new Div();
        this.hint.setText("Sie haben keine Nachrichten.");
        setDivStyle(hint);

        Div hint2 = new Div();
        hint2.setText("Es wurde keine Nachricht ausgewählt.");
        setDivStyle(hint2);

        VerticalLayout inbox = new VerticalLayout(hint, grid);
        VerticalLayout reply = new VerticalLayout(hint2);

        // Compose both sides
        this.splitLayout = new SplitLayout(inbox, reply);
        this.splitLayout.setSplitterPosition(1000);
        this.splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);

        this.splitLayout.setSizeFull();
        this.splitLayout.setHeight("100%");
        setSizeFull();
        setHeight("100%");
        add(this.splitLayout);
    }


    private void toggleReply(MessageDTO message, boolean selected) throws DatabaseUserException {
        if(selected)
        {
            // "Header" for message (Data that is in grid as well)
            Label sender = new Label("Absender:");
            Label senderVal = new Label(messageControl.getPersonName(message.getSender()));
            Label subject = new Label("Betreff:");
            Label subjectVal = new Label(messageControl.getSubject(message.getId()));
            Label date = new Label("Datum:");
            Label dateVal = new Label(message.getDate().toString());
            Label mess = new Label("Nachricht:");

            styling(sender, senderVal, subject, subjectVal, date, dateVal, mess);

            // Layout for when a message has been chosen

            // The actual message
            Paragraph messageContent = new Paragraph(message.getMessageText());
            messageContent.getElement().getStyle().set("background-color", "grey");
            messageContent.getElement().getStyle().set("padding", "10px");
            messageContent.setWidth("80%");
            messageContent.setMinHeight("150px");

            // Visit profile button
            Button profile = new Button("Profil besuchen");
            //profileButton(message, profile);

            // Delete button for messages
            Button delete = new Button("Nachricht löschen");


                       delete.addClickListener(e -> {
                Runnable yesRunnable = () -> {
                    try {
                        this.removeMessage(message);
                    } catch (DatabaseUserException ex) {
                        ex.printStackTrace();
                    }
                    splitLayout.setSplitterPosition(1000);
                    grid.deselectAll();
                    cleanSecondary();
                    if(grid.getColumns().isEmpty())
                        splitLayout.addToPrimary(hint);
                };
            });



            // Reply
            TextArea messageReply = new TextArea();
            messageReply.setLabel("Antwort:");
            messageReply.setWidth("80%");
            messageReply.setMinHeight("150px");
            messageReply.setErrorMessage("Bitte geben Sie eine Nachricht ein.");

            // Buttons for replying and cancelling
            Button replyButton = new Button("Senden");
            Button cancelButton = new Button("Verwerfen");

            replyButton(messageReply, replyButton);
            cancelButton.addClickListener(e -> messageReply.setValue(""));

            HorizontalLayout header = new HorizontalLayout(sender, senderVal, subject, subjectVal, date, dateVal);
            HorizontalLayout hButtons1 = new HorizontalLayout(profile, delete);
            HorizontalLayout hButtons2 = new HorizontalLayout(replyButton, cancelButton);

            VerticalLayout inboxReply = new VerticalLayout(header, mess, messageContent, hButtons1,
                    messageReply, hButtons2);
            inboxReply.setWidth("100%");

            splitLayout.remove(splitLayout.getSecondaryComponent());
            splitLayout.addToSecondary(inboxReply);
        }
        else
            cleanSecondary();
    }

    private void replyButton(TextArea messageReply, Button replyButton) {
        replyButton.addClickListener(e -> {
            // Check if messageReply field is empty. If not, send an answer to the sender of the email
            if(!Objects.equals(messageReply.getValue(), ""))
            {
                MessageDTO newMessage;
                if(grid.getSelectionModel().getFirstSelectedItem().isPresent())
                {
                    newMessage = messageControl.prepareSending(
                            grid.getSelectionModel().getFirstSelectedItem().get(),
                            messageReply.getValue());
                    try {
                        // Send message, show confirmation, deselect content
                        messageControl.sendMessage(newMessage);
                        messageReply.setValue("");
                        splitLayout.setSplitterPosition(1000);
                        grid.deselectAll();
                        cleanSecondary();

                        Dialog dialog = new Dialog();
                        dialog.add("Ihre Nachricht wurde gesendet!");
                        dialog.open();

                    } catch (DatabaseUserException ex) {
                        ex.printStackTrace();
                        Dialog dialog = new Dialog();
                        dialog.add("Während dem Senden der Nachricht ist ein Fehler aufgetreten. Bitte kontaktieren " +
                                "Sie den Administrator dieser Webseite.");
                        dialog.open();
                    }
                }
                else
                {
                    Dialog dialog = new Dialog();
                    dialog.add("Während dem Senden der Nachricht ist ein Fehler aufgetreten. Bitte kontaktieren " +
                            "Sie den Administrator dieser Webseite.");
                    dialog.open();
                }
            }
            else
                messageReply.setInvalid(true);
        });
    }


    private void styling(Label sender, Label senderVal, Label subject, Label subjectVal, Label date, Label dateVal, Label mess) {
        // Styling
        for(Label label : new Label[] {sender, senderVal, subject, subjectVal, date, dateVal, mess})
            label.getElement().getStyle().set("font-size", "14px");

        for(Label label : new Label[] {sender, subject, date})
            label.getElement().getStyle().set("font-weight", "bold");
    }

    // Layout when a message has been deselected
    private void cleanSecondary() {
        this.hint = new Div();
        this.hint.setText("Es wurde keine Nachricht ausgewählt.");
        setDivStyle(this.hint);
        this.splitLayout.remove(this.splitLayout.getSecondaryComponent());
        VerticalLayout vHint = new VerticalLayout(this.hint);
        this.splitLayout.addToSecondary(vHint);
    }


    private static void setDivStyle(Div item) {
        item.getStyle().set("padding", "var(--lumo-size-l)")
                .set("text-align", "center").set("font-style", "italic")
                .set("color", "var(--lumo-contrast-70pct)");
    }




    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            if(!Objects.equals(event, "")) {
                this.setupGrid();
                this.refreshGrid();
            }
        } catch (Exception exception) {
            LOGGER.info(Globals.LogMessage.LOG ,  exception.toString());
        }
    }

    private void removeMessage(MessageDTO message) throws DatabaseUserException {
        if (message == null)
            return;
        messages.remove(message);
        messageControl.deleteMessage(message);
        this.refreshGrid();
    }

}
