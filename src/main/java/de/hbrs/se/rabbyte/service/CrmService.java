package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.*;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ApplicationRepository applicationRepository;
    private final BusinessRepository businessRepository;
    private final PersonRepository personRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final StudentRepository studentRepository;

    public CrmService(ApplicationRepository applicationRepository, BusinessRepository businessRepository,
                      PersonRepository personRepository, JobAdvertisementRepository jobAdvertisementRepository,
                      StudentRepository studentRepository){

        this.applicationRepository = applicationRepository;
        this.businessRepository = businessRepository;
        this.personRepository = personRepository;
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
        return personRepository.count();
    }
    public void deleteUser(Person person){
        personRepository.delete(person);
    }
    public void saveUser(Person person){
        if(person == null){
            System.err.println("User is null.");
            return;
        }
        personRepository.save(person);
    }
    public PersonDTO findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public PersonDTO findGeneralUserById(int nutzerid){
       return personRepository.findGeneralUserById(nutzerid);
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
