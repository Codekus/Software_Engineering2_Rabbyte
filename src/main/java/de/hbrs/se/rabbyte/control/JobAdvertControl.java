package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.JobAdvertFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobAdvertControl {

        @Autowired
        private JobAdvertisementRepository repository;

        public void createJobAdvert(JobAdvertisementDTO jobAdvertisementDTO, BusinessDTO businessDTO){

                JobAdvertisement jobAdvertisement = JobAdvertFactory.publishJobAdvert(jobAdvertisementDTO, businessDTO);

                this.repository.save(jobAdvertisement);
        }

        //ToDo
        /*
        public List<JobAdvertisementDTO> readAllJobAdverts() {
                return repository.findJobAdvertisementBy...?;
        }
        */
}
