package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;

public class JobAdvertFactory {

        public static JobAdvertisement publishJobAdvert(JobAdvertisementDTO jobAdvertisementDTO, BusinessDTO businessDTO) {

                JobAdvertisement jobAdvertisement = new JobAdvertisement();

                jobAdvertisement.setId( jobAdvertisementDTO.getId() );
                jobAdvertisement.setText( jobAdvertisementDTO.getText() );
                jobAdvertisement.setType( jobAdvertisementDTO.getType() );
                jobAdvertisement.setTitle( jobAdvertisementDTO.getTitle() );
                jobAdvertisement.setBusiness( jobAdvertisementDTO.getBusiness() );

                return jobAdvertisement;
        }
}
