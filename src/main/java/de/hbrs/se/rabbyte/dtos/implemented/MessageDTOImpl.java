package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.MessageDTO;

import java.time.LocalDateTime;

public class MessageDTOImpl implements MessageDTO {

    private int id;
    private int senderId;
    private int receiverId;
    private String messageText;
    private String title;
    private LocalDateTime date;
    private boolean read;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSender(int senderId) {
        this.id = id;
    }

    public int getSender() {
        return senderId;
    }

    public void setReceiver(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getReceiver() {
        return receiverId;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean getRead() {
        return read;
    }

}
