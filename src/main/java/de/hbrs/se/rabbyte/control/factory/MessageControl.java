package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.control.MessageFactory;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.entities.Message;
import de.hbrs.se.rabbyte.repository.MessageRepository;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.util.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class MessageControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageControl.class);


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<MessageDTO> getMessages(int recipient) {
        return messageRepository.findMessagesByRecipient(recipient);
    }

    public MessageDTO prepareSending(MessageDTO selectedMessage, String message) {
        MessageDTOImpl newMessage = new MessageDTOImpl();
        newMessage.setMessageText(message);
        newMessage.setSender(selectedMessage.getSender());
        newMessage.setReceiver(selectedMessage.getSender());
        newMessage.setTitle(selectedMessage.getTitle());
        newMessage.setDate(LocalDateTime.now());
        return newMessage;
    }

    // Senden einer Nachricht an einen Nutzer
    public Message sendMessage(MessageDTO messageDTO) throws DatabaseUserException {
        try {
            Message message = MessageFactory.createMessage(messageDTO);
            this.messageRepository.save(message);
            return message;
        } catch (Exception exception) {
            LOGGER.info("INFO Verification: {}"  ,  exception.getMessage());
            if (exception instanceof org.springframework.dao.DataAccessResourceFailureException) {
                throw new DatabaseUserException(Globals.LogMessage.CONNECTED);
            } else {
                throw new DatabaseUserException(Globals.LogMessage.ERROR);
            }
        }
    }


}
