package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message" , schema = "rabbyte")
public class Message {

    private int id;
    private int senderId;
    private int receiverId;
    private String messageText;
    private String title;
    private LocalDateTime date;
    private boolean read;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "message_id"
    )
    @SequenceGenerator(
            name = "message_id",
            sequenceName = "rabbyte.seq_message_id",
            allocationSize=1
    )
    @Column(name = "message_id")
    public int getId() {
        return id;
    }
    public void setId(int MessageId) {
        this.id = MessageId;
    }

    @Column(name = "sender_id")
    public int getSender() {
        return senderId;
    }
    public void setSender(int senderId ) {
        this.senderId = senderId;
    }

    @Column(name = "receiver_id")
    public int getReceiver() {
        return receiverId;
    }

    public void setReceiver(int recieverId) {
        this.receiverId = recieverId;
    }

    @Column(name = "message_text")
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "date")
    public LocalDateTime getDate(){
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "read")
    public boolean getRead() {
        return read;
    }
    public void setRead(boolean read) {
        this.read = read;
    }
}
