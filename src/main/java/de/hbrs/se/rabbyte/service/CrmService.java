package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.dtos.*;
import de.hbrs.se.rabbyte.entities.*;
import de.hbrs.se.rabbyte.repository.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrmService {
    private static final Logger LOGGER = Logger.getLogger(CrmService.class.getSimpleName());

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
        return jobAdvertisementRepository.search(searchText);

    }
    public List<JobAdvertisement>filterJobAdvertisementsByKeywordList(List<JobAdvertisement> jobAdvertisements,List<String> keywordList) {
        for (String keyword : keywordList){
            jobAdvertisements = jobAdvertisements.stream().filter(jobAdvertisement -> (findJobAdvertisements(keyword).stream().map(JobAdvertisement::getId).collect(Collectors.toList()).contains(jobAdvertisement.getId()))).collect(Collectors.toList());
    }
        return jobAdvertisements;
    }
    public List<JobAdvertisement>filterJobAdvertisementsByKeyword(List<JobAdvertisement> jobAdvertisements,String keyword,String indicator){
        if(indicator.equals("Unternehmen")){
           return jobAdvertisements.stream().filter(jobAdvertisement -> (jobAdvertisement.getBusiness().getBusinessName()).equals(keyword)).collect(Collectors.toList());
        }else if(indicator.equals("Art")) {
            return jobAdvertisements.stream().filter(jobAdvertisement -> (jobAdvertisement.getType()).equals(keyword)).collect(Collectors.toList());
        }
        return filterJobAdvertisementsByKeywordList(jobAdvertisements, Collections.singletonList(keyword));
    }
    public JobAdvertisementDTO findJobAdvertisementById(int id){
        return jobAdvertisementRepository.findJobAdvertisementById(id);
    }

    public List<JobAdvertisement> findJobAdvertisementByBusId(int id){
        return jobAdvertisementRepository.searchByID(id);
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

    public List<String> getAllJobAdvertisementTypes(){
        return jobAdvertisementRepository.findAll().stream().map(JobAdvertisement::getType).distinct().collect(Collectors.toList());
    }

    //PersonRepository

    public long countPerson(){
        return personRepository.count();
    }
    public void deletePerson(Person person){
        personRepository.delete(person);
    }
    public void savePerson(Person person){
        if(person == null){
            System.err.println("Person is null.");
            return;
        }
        personRepository.save(person);
    }
    public PersonDTO findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public PersonDTO findPersonById(int personId){
       return personRepository.findPersonById(personId);
    }

    //StudentRepository
    public long countStudent(){
        return studentRepository.count();
    }
    public void deleteStudentById(int personId){
        studentRepository.deleteById(personId);
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

    public StudentDTO findStudentById(int personId){
        return studentRepository.findStudentById(personId);
    }

    //BusinessRepository

    public long countBusiness(){
        return businessRepository.count();
    }
    public void deleteBusinessById(int personId){
        businessRepository.deleteById(personId);
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
    public BusinessDTO findBusinessById(int personId){
        return businessRepository.findBusinessById(personId);
    }

    public List<String> findAllBusinessNames(){
        return businessRepository.findAll().stream().map(Business::getBusinessName).distinct().collect(Collectors.toList());
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
