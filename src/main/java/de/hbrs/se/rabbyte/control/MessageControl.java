package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.MessageFactory;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.entities.Message;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.repository.*;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MessageControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageControl.class);
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public List<MessageDTO> getMessages(int receiver) {
        return messageRepository.findMessagesByReceiver(receiver);
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }


    public MessageDTO prepareSending(MessageDTO selectedMessage, String message) {
        MessageDTOImpl newMessage = new MessageDTOImpl();
        newMessage.setMessageText(message);
        newMessage.setSender(selectedMessage.getSender());
        newMessage.setReceiver(selectedMessage.getSender());
        newMessage.setTitle(selectedMessage.getTitle());
        newMessage.setDate(LocalDate.now());
        return newMessage;
    }

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

    public String getEmail() {
        return getCurrentUser();
    }

    public void saveMessage(MessageDTOImpl messageDTO) {

        messageDTO.setDate(LocalDate.now());

        String email = getEmail();
        
        try {
            int id = getId(email);
            messageDTO.setSender(id);


        } catch (Exception e) {
            LOGGER.info("INFO: {}" ,  e.getMessage());
        }

        Message message = MessageFactory.createMessage(messageDTO);

        messageRepository.save(message);

    }

    public int getId(String email) {
        PersonDTO person = personRepository.findByEmail(email);
        return person.getId();

    }

    public Message deleteMessage(MessageDTO messageDTO) throws DatabaseUserException {
        try {
            Message message = MessageFactory.createMessage(messageDTO);
            this.messageRepository.delete(message);
            return message;
        } catch (Exception exception) {
            LOGGER.info(Globals.LogMessage.LOG, exception.toString());
            if (exception instanceof org.springframework.dao.DataAccessResourceFailureException) {
                throw new DatabaseUserException(Globals.LogMessage.CONNECTED);
            } else {
                throw new DatabaseUserException(Globals.LogMessage.ERROR);
            }
        }
    }

    public String getPersonName(int id) throws DatabaseUserException {
        try {
            boolean type = getRole(Utils.getCurrentPerson());
            if(true) {
                return studentRepository.findStudentById(id).getFirstName() + " " +
                studentRepository.findStudentById(id).getLastName();
            }
            else {
                businessRepository.findBusinessByBusinessID(id);
                return "Business Name";
            }
        } catch (Exception exception) {
            LOGGER.info(Globals.LogMessage.LOG,  exception.toString());
            if (exception instanceof org.springframework.dao.DataAccessResourceFailureException) {
                throw new DatabaseUserException(Globals.LogMessage.CONNECTED);
            } else {
                throw new DatabaseUserException(Globals.LogMessage.ERROR);
            }
        }
    }

    public String getSubject(int messageId) throws DatabaseUserException {
        try {
            return this.messageRepository.findMessageById(messageId).getTitle();
        } catch (Exception exception) {
            LOGGER.info(Globals.LogMessage.LOG ,  exception.toString());
            if (exception instanceof org.springframework.dao.DataAccessResourceFailureException) {
                throw new DatabaseUserException(Globals.LogMessage.CONNECTED);
            } else {
                throw new DatabaseUserException(Globals.LogMessage.ERROR);
            }
        }
    }

    public boolean getRole(PersonDTO user){

        if (personRepository.findByEmail(getEmail()) != null) {
            return true;
        }
        else
            {
                return false;
            }
    }


    public Message setMessageAsRead(MessageDTO message) throws DatabaseUserException {
        MessageDTOImpl readMessage = new MessageDTOImpl();
        readMessage.setId(message.getId());
        readMessage.setSender(message.getSender());
        readMessage.setReceiver(message.getReceiver());
        readMessage.setTitle(message.getTitle());
        readMessage.setDate(message.getDate());
        readMessage.setMessageText(message.getMessageText());

        try {
            Message updatedMessage = MessageFactory.createMessage(readMessage);
            updatedMessage.setRead(true);
            messageRepository.save(updatedMessage);
            return updatedMessage;
        } catch (Exception exception) {
            LOGGER.info(Globals.LogMessage.LOG ,  exception.toString());
            if (exception instanceof org.springframework.dao.DataAccessResourceFailureException) {
                throw new DatabaseUserException(Globals.LogMessage.CONNECTED);
            } else {
                throw new DatabaseUserException(Globals.LogMessage.ERROR);
            }
        }
    }

}
