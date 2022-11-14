package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Application;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.User;
import de.hbrs.se.rabbyte.repository.*;
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

    //JobAdvertisementRepository
    public List<JobAdvertisement> findJobAdvertisements(String searchText){
        List<JobAdvertisement> jobAdvertisementsRes = jobAdvertisementRepository.search(searchText);
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

    //GeneralUserRepository

    public long countUser(){
        return generalUserRepository.count();
    }
    public void deleteUser(User user){
        generalUserRepository.delete(user);
    }
    public void saveUser(User user){
        if(user == null){
            System.err.println("User is null.");
            return;
        }
        generalUserRepository.save(user);
    }
    public GeneralUserDTO findByEmail(String email){
        return generalUserRepository.findByEmail(email);
    }

    public GeneralUserDTO findGeneralUserById(int nutzerid){
       return generalUserRepository.findGeneralUserById(nutzerid);
    }

    //StudentRepository
    public long countStudents(){
        return studentRepository.count();
    }
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }
    public void saveStudent(Student student){
        if(student == null){
            System.err.println("Student is null.");
            return;
        }
        studentRepository.save(student);
    }

    public StudentDTO findByFirstNameAndLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    public StudentDTO findStudentById(int id){
        return studentRepository.findStudentById(id);
    }

    //BusinessRepository

    public long countBusiness(){
        return businessRepository.count();
    }
    public void deleteBusiness(Business business){
        businessRepository.delete(business);
    }
    public void saveBusiness(Business business){
        if(business == null){
            System.err.println("Business is null.");
            return;
        }
        businessRepository.save(business);
    }

    public BusinessDTO findBusinessByBusinessName(String name){
        return businessRepository.findBusinessByBusinessName(name);
    }

    //ApplicationRepository

    public long countApplication(){
        return applicationRepository.count();
    }
    public void deleteApplication(Application application){
        applicationRepository.delete(application);
    }
    public void saveApplication(Application application){
        if(application == null){
            System.err.println("Application is null.");
            return;
        }
        applicationRepository.save(application);
    }
    public ApplicationDTO findApplicationById(int id){
        return applicationRepository.findApplicationById(id);
    }

}
