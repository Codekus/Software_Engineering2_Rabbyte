package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.ApplicationFactory;
import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.entities.Application;
import de.hbrs.se.rabbyte.exception.DatabaseUserException;
import de.hbrs.se.rabbyte.repository.ApplicationRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ApplicationControl {

    @Autowired
    SecurityService securityService;

    @Autowired
    private ApplicationRepository repository;

    public void apply(MessageControl messageControl, MessageDTOImpl messageDTO, ApplicationDTO applicationDTO){
        messageDTO.setTitle(createTitle(applicationDTO));
        messageDTO.setMessageText(applicationDTO.getApplicationText());
        messageDTO.setDate(applicationDTO.getDate());
        messageDTO.setSender(applicationDTO.getStudent().getId());
        messageDTO.setReceiver(applicationDTO.getJobAdvertisement().getBusiness().getId());


        try {
            messageControl.sendMessage(messageDTO);
        } catch (DatabaseUserException e) {
            throw new RuntimeException(e);
        }
    }

    public void createApplication(ApplicationDTO applicationDTO) {
        Application application = ApplicationFactory.createApplication(applicationDTO);

        this.repository.save(application);
    }

    public boolean validateDescription(String desc){
        Pattern pattern = Pattern.compile("([A-Za-z0-9.:/()_%+-](\\s)*)+");
        Matcher matcher = pattern.matcher(desc);

        return matcher.find();
    }

    private String createTitle(ApplicationDTO applicationDTO) {
        return applicationDTO.getJobAdvertisement().getId() + " " + applicationDTO.getStudent().getLastName();
    }
}
