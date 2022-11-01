package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.repository.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ApplicationRepository applicationRepository;
    private final BusinessRepository businessRepository;
    private final GeneralUserRepository generalUserRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final StudentRepository studentRepository;

    public CrmService(ApplicationRepository applicationRepository, BusinessRepository businessRepository,
                      GeneralUserRepository generalUserRepository, JobAdvertisementRepository jobAdvertisementRepository,
                      StudentRepository studentRepository){

        this.applicationRepository = applicationRepository;
        this.businessRepository = businessRepository;
        this.generalUserRepository = generalUserRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.studentRepository = studentRepository;
    }

    public List<JobAdvertisement> findJobAdvertisements(String searchText){
        //return nothing if no search input is given
        //else return everything that contains the search input

        /*if(searchText == null || searchText.isEmpty()){
            return null;
        }else{ */
        return jobAdvertisementRepository.search(searchText);

    }

    public long countJobAdvertisements(){
        return jobAdvertisementRepository.count();
    }
    public void deleteJobAdvertisement(JobAdvertisement jobAdvertisement){
        jobAdvertisementRepository.delete(jobAdvertisement);
    }
    public void saveJobAdvertisement(JobAdvertisement jobAdvertisement){
        if(jobAdvertisement == null){
            System.err.println("JobAdvertisement is null.");
            return;
        }
        jobAdvertisementRepository.save(jobAdvertisement);
    }

}
