package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.entities.Application;
import de.hbrs.se.rabbyte.entities.Message;

public class ApplicationFactory {

    private ApplicationFactory() {
        throw new IllegalStateException("Factory Class");
    }

    public static void createApplication(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setId(applicationDTO.getId());
        application.setJobAdvertisement(applicationDTO.getJobAdvertisement());
        application.setApplicationText(applicationDTO.getApplicationText());
        application.setStudent(applicationDTO.getStudent());
        application.setDate(applicationDTO.getDate());
    }
}
