package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class StudentRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    StudentRepository studentRepository;

    @BeforeMethod
    void setup(){
        this.studentRepository = applicationContext.getBean(StudentRepository.class);
    }


    @Test
    void findStudentById() {
        assertEquals("Max" , studentRepository.findStudentById(20000050).getFirstName());

    }
    @Test
    void findStudentByIdDTO() {
        assertTrue( studentRepository.findStudentById(20000050) instanceof StudentDTO);

    }

    @Test
    void  findStudentByIdNull() {
        assertNull(  studentRepository.findStudentById(20000080));
    }

    @Test
    void findByFirstNameAndLastName() {
        assertEquals(20000050 , studentRepository.findByFirstNameAndLastName("Max" , "Mustermann").getId());
    }

    @Test
    void findByFirstNameAndLastNameNull() {
        assertNull(  studentRepository.findByFirstNameAndLastName("Tom" , "Mustermann"));

    }
}