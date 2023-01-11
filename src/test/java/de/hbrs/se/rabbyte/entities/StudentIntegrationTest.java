package de.hbrs.se.rabbyte.entities;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class StudentIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

    StudentRepository studentRepository;

    Student student;

    StudentDTO studentDTO;

    private String firstName;
    private String lastName;
    private String faculty;
    private String email;
    private String studentHashPw;
    private int plz;
    private String city;
    private String country;
    private String street;
    private String streetNumber;


    @BeforeMethod
    void setUp(){
        this.studentRepository = applicationContext.getBean(StudentRepository.class);
        firstName = "Max";
        lastName = "Mustermann";
        faculty = "Informatik";
        email = "max@gmx.de";
        studentHashPw = "8e0ac6815b51b73afd18c0ffd5d1eb0cdd3fb8ece16ad685f9199b1c4b55435bfde9ba85616ceee5072418c76d0de5086a0ae002f0be1665cd33a5af33b2f24f";
        plz = 12345;
        city = "Musterstadt";
        country = "DE";
        street = "Musterstadt";
        streetNumber = "1";
        studentDTO = studentRepository.findStudentById(20000050);
        student = PersonFactory.createStudent(studentDTO);
    }

    @Test
    void studentId() {
        assertEquals(20000050, studentDTO.getId());
        assertEquals(20000050, student.getId());
    }

    @Test
    void studentFirstName() {

        assertEquals(firstName, studentDTO.getFirstName());
        assertEquals(firstName, student.getFirstName());
    }

    @Test
    void studentLastName() {

        assertEquals(lastName, studentDTO.getLastName());
        assertEquals(lastName, student.getLastName());
    }

    @Test
    void studentFaculty() {

        assertEquals(faculty, studentDTO.getFaculty());
        assertEquals(faculty, student.getFaculty());
    }

    @Test
    void studentEmail() {

        assertEquals(email, studentDTO.getEmail());
        assertEquals(email, student.getEmail());
    }
    @Test
    void studentPassword() {

        assertEquals(studentHashPw, studentDTO.getPassword());
    }
    @Test
    void studentPlz() {


        assertEquals(plz, student.getPlz());
    }
    @Test
    void studentCity() {

        assertEquals(city, studentDTO.getCity());
        assertEquals(city, student.getCity());
    }
    @Test
    void studentCountry() {

        assertEquals(country, studentDTO.getCountry());
        assertEquals(country, student.getCountry());
    }

    @Test
    void studentStreet() {

        assertEquals(street, studentDTO.getStreet());
        assertEquals(street, student.getStreet());
    }

    @Test
    void studentStreetNumber() {

        assertEquals(streetNumber, studentDTO.getStreetNumber());
        assertEquals(streetNumber, student.getStreetNumber());
    }






}
