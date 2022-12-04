package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.JobAdvertFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.exception.AuthException;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JobAdvertControl {

        @Autowired
        private JobAdvertisementRepository repository;

        public void createJobAdvert(JobAdvertisementDTO jobAdvertisementDTO){

                JobAdvertisement jobAdvertisement = JobAdvertFactory.publishJobAdvert(jobAdvertisementDTO);

                this.repository.save(jobAdvertisement);
        }

        public boolean validateTitle(String title){
                Pattern pattern = Pattern.compile("([A-Za-z0-9.:/()_%+-](\\s)*)+");
                Matcher matcher = pattern.matcher(title);

                return matcher.find();
        }

        public boolean validateType(String type){
                Pattern pattern = Pattern.compile("([A-Za-z/])+");
                Matcher matcher = pattern.matcher(type);

                return matcher.find();
        }

        public boolean validateDescription(String desc){
                Pattern pattern = Pattern.compile("([A-Za-z0-9.:/()_%+-](\\s)*)+");
                Matcher matcher = pattern.matcher(desc);

                return matcher.find();
        }
        //ToDo
        /*
        public List<JobAdvertisementDTO> readAllJobAdverts() {
                return repository.findJobAdvertisementBy...?;
        }
        */

        public void editJobAdvert(JobAdvertisementDTO jobAdvertisementDTO){

                this.repository.editJobAdvert(
                        jobAdvertisementDTO.getId(),
                        jobAdvertisementDTO.getBusiness(),
                        jobAdvertisementDTO.getTitle(),
                        jobAdvertisementDTO.getText(),
                        jobAdvertisementDTO.getType()
                );
        }
}
