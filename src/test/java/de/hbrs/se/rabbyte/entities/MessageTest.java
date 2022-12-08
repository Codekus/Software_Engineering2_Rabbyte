package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.bouncycastle.cms.Recipient;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private Message message;
    private int id;
    private int senderId;
    private int receiverId;
    private String messageText;
    private String title;
    private LocalDateTime date;
    private boolean read;



    @BeforeEach
    void setUp() {
        id = 1000;
        senderId = 2000;
        receiverId = 3000;
        messageText = "Message Text Test";
        title = "Message Title";
        date = LocalDateTime.of(2000, 1,2,20, 50, 30);
        read = false;

        message = new Message();
        message.setMessageText(messageText);
        message.setId(id);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setTitle(title);
        message.setDate(date);
        message.setRead(read);

    }

    @Test
    void getId() {
        assertEquals(id , message.getId());
    }

    @Test
    void getSender() {
        assertEquals(senderId , message.getSender());
    }

    @Test
    void getReceiver() {
        assertEquals(receiverId , message.getReceiver());
    }

    @Test
    void getMessageText() {
        assertEquals(messageText , message.getMessageText());
    }

    @Test
    void getTitle() {

        assertEquals(title , message.getTitle());
    }

    @Test
    void getDate() {

        assertEquals(date , message.getDate());
    }

    @Test
    void getRead() {
        assertEquals(read , message.getRead());
    }
}