package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.entities.Message;

public class MessageFactory {
    private MessageFactory() {
        throw new IllegalStateException("Factory Class");
    }
    public static Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setReceiver(messageDTO.getReceiver());
        message.setSender(messageDTO.getSender());
        message.setMessageText(messageDTO.getMessageText());
        message.setTitle(messageDTO.getTitle());
        message.setDate(messageDTO.getDate());
        message.setRead(false);
        return message;
    }

}
