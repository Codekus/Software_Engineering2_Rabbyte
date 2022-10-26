package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.publisher.JobAdvertPublisher;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobAdvertControl {

        @Autowired
        private JobAdvertisementRepository repository;

        public void creatJobAdvert(JobAdvertisementDTO jobAdvertisementDTO, BusinessDTO businessDTO){

                JobAdvertisement jobAdvertisement = JobAdvertPublisher.publishJobAdvert(jobAdvertisementDTO, businessDTO);

                this.repository.save(jobAdvertisement);
        }

        //ToDo
        /*
        public List<JobAdvertisementDTO> readAllJobAdverts() {
                return repository.findJobAdvertisementBy...?;
        }
        */
}
