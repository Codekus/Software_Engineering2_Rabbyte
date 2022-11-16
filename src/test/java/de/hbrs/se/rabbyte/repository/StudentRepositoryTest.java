package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

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