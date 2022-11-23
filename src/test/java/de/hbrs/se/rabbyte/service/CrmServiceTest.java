package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.control.factory.JobAdvertFactory;
import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.entities.*;
import de.hbrs.se.rabbyte.repository.*;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
public class CrmServiceTest {


    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JobAdvertisementRepository jobAdvertisementRepository;
    @Autowired
    private StudentRepository studentRepository;

    private CrmService crmService = null;

    @BeforeEach
    void setUp() {
        crmService = new CrmService(applicationRepository, businessRepository, personRepository, jobAdvertisementRepository, studentRepository);
    }


    @Test
    @DisplayName("Counting Entities Test")
    public void countEntitiesTest() {
        assertAll("Should count the amount of entities present in the database",
                () -> assertEquals(1, crmService.countJobAdvertisements(), "JobAdvertisement counting failed"),
                () -> assertEquals(2, crmService.countPerson(), "User counting failed"),
                () -> assertEquals(1, crmService.countBusiness(), "Business counting failed"),
                () -> assertEquals(1, crmService.countStudent(), "Student counting failed"),
                () -> assertEquals(1, crmService.countApplication(), "Application counting failed")
        );
    }

    @Test
    @DisplayName("Delete Test")
    public void deleteTest() {
        //ApplicationDTO application =  crmService.findApplicationById(10000001);
        Business business = PersonFactory.createBusiness(crmService.findBusinessById(20000090));
        Student student = PersonFactory.createStudent(crmService.findStudentById(20000050));
        JobAdvertisement jobAdvertisement = JobAdvertFactory.publishJobAdvert(crmService.findJobAdvertisementById(30000087));

        crmService.deleteJobAdvertisementById(jobAdvertisement.getId());
        crmService.deleteBusinessById(business.getId());
        crmService.deleteStudentById(student.getId());


        assertAll("database should be empty",
                () -> assertEquals(0, crmService.countJobAdvertisements(), "JobAdvertisement deletion failed"),
                () -> assertEquals(0, crmService.countPerson(), "User deletion failed"),
                () -> assertEquals(0, crmService.countBusiness(), "Business deletion failed"),
                () -> assertEquals(0, crmService.countApplication(), "Application deletion failed"),
                () -> assertEquals(0, crmService.countStudent(), "Student deletion failed")
        );



    }
    @Test
    @DisplayName("Find-By Tests")
    public void findByTests() {


        assertAll("FindBy Methods Test",
                () -> assertEquals("MoneyInc", crmService.findBusinessById(20000090).getBusinessName(), "findBusinessById failed"),
                () -> assertEquals(20000090, crmService.findBusinessByBusinessName("MoneyInc").getId(), "findBusinessByBusinessName failed"),
                () -> assertEquals(20000090, crmService.findByEmail("money@gmx.de").getId(), "findByEmail failed"),
                () -> assertEquals("Max", crmService.findStudentById(20000050).getFirstName(), "findStudentById failed"),
                () -> assertEquals(20000090, crmService.findJobAdvertisementById(30000087).getBusiness().getId(), "findJobAdvertisementById failed"),
                () -> assertEquals(20000090, crmService.findJobAdvertisements("Advertisement").get(0).getBusiness().getId(), "findJobAdvertisements failed"),
                () -> assertEquals(20000050, crmService.findByFirstNameAndLastName("Max","Mustermann").getId(), "findByFirstNameAndLastName failed"),
                () -> assertEquals("money@gmx.de", crmService.findPersonById(20000090).getEmail(), "findPersonById failed")

        );



    }


    @Ignore
    @Test
    @DisplayName("Save Test")
    public void saveTest() {

        Business business = PersonFactory.createBusiness(crmService.findBusinessById(20000090));
        Student student = PersonFactory.createStudent(crmService.findStudentById(20000050));
        JobAdvertisement jobAdvertisement = JobAdvertFactory.publishJobAdvert(crmService.findJobAdvertisementById(30000087));

        business.setId(2);
        jobAdvertisement.getBusiness().setId(2);
        student.setId(3);


        crmService.saveBusiness(business);
        crmService.saveStudent(student);
        crmService.saveJobAdvertisement(jobAdvertisement);


        assertAll("database should be at starting point",
                () -> assertEquals(1, crmService.countJobAdvertisements(), "JobAdvertisement creation failed"),
                () -> assertEquals(2, crmService.countPerson(), "User creation failed"),
                () -> assertEquals(1, crmService.countBusiness(), "Business creation failed"),
                () -> assertEquals(1, crmService.countStudent(), "Student creation failed")
        );
    }


}
