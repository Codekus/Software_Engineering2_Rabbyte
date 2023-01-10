package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationControl {

    @Autowired
    SecurityService securityService;

    public void apply(MessageControl messageControl, MessageDTOImpl messageDTO, Integer business, String title, String messageText){
        messageDTO.setTitle(title);
        messageDTO.setMessageText(messageText);
        messageDTO.setDate(LocalDate.now());
        messageDTO.setSender(securityService.getAuthenticatedUser().getId());
        messageDTO.setReceiver(business);


        try {
            messageControl.sendMessage(messageDTO);
        } catch (DatabaseUserException e) {
            throw new RuntimeException(e);
        }
    }
}
