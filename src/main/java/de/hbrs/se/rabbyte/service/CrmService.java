package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.dtos.*;
import de.hbrs.se.rabbyte.entities.Application;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.User;
import de.hbrs.se.rabbyte.repository.*;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@Service
public class CrmService {
    private static final Logger LOGGER = Logger.getLogger(CrmService.class.getSimpleName());

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

    //JobAdvertisementRepository
    public List<JobAdvertisement> findJobAdvertisements(String searchText){
        return jobAdvertisementRepository.search(searchText);

    }
    public JobAdvertisementDTO findJobAdvertisementById(int id){
        return jobAdvertisementRepository.findJobAdvertisementById(id);
    }

    public long countJobAdvertisements(){
        return jobAdvertisementRepository.count();
    }
    public void deleteJobAdvertisementById(int id){
        jobAdvertisementRepository.deleteById(id);
    }
    public void saveJobAdvertisement(JobAdvertisement jobAdvertisement){
        if(jobAdvertisement == null){
            LOGGER.log(Level.INFO,"JobAdvertisement is null.");
            return;
        }
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    //GeneralUserRepository

    public long countUser(){
        return generalUserRepository.count();
    }
    public void deleteUserById(int userId){
        generalUserRepository.deleteById(userId);
    }
    public void saveUser(User user){
        if(user == null){
            LOGGER.log(Level.INFO,"User is null.");
            return;
        }
        generalUserRepository.save(user);
    }
    public GeneralUserDTO findByEmail(String email){
        return generalUserRepository.findByEmail(email);
    }

    public GeneralUserDTO findGeneralUserById(int userId){
       return generalUserRepository.findGeneralUserById(userId);
    }

    //StudentRepository
    public long countStudent(){
        return studentRepository.count();
    }
    public void deleteStudentById(int userId){
        studentRepository.deleteById(userId);
    }
    public void saveStudent(Student student){
        if(student == null){
            LOGGER.log(Level.INFO,"Student is null.");
            return;
        }
        studentRepository.save(student);
    }

    public StudentDTO findByFirstNameAndLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    public StudentDTO findStudentById(int userId){
        return studentRepository.findStudentById(userId);
    }

    //BusinessRepository

    public long countBusiness(){
        return businessRepository.count();
    }
    public void deleteBusinessById(int userId){
        businessRepository.deleteById(userId);
    }
    public void saveBusiness(Business business){
        if(business == null){
            LOGGER.log(Level.INFO,"Business is null.");
            return;
        }
        businessRepository.save(business);
    }

    public BusinessDTO findBusinessByBusinessName(String name){
        return businessRepository.findBusinessByBusinessName(name);
    }
    public BusinessDTO findBusinessById(int userId){
        return businessRepository.findBusinessById(userId);
    }

    //ApplicationRepository

    public long countApplication(){
        return applicationRepository.count();
    }
    public void deleteApplicationById(int id){
        applicationRepository.deleteById(id);
    }
    public void saveApplication(Application application){
        if(application == null){
            LOGGER.log(Level.INFO,"Application is null.");
            return;
        }
        applicationRepository.save(application);
    }
    public ApplicationDTO findApplicationById(int id){
        return applicationRepository.findApplicationById(id);
    }

}
