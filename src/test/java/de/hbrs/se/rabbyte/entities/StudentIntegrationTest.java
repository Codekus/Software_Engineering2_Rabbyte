package de.hbrs.se.rabbyte.entities;

import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
public class StudentIntegrationTest {

    @Autowired
    StudentRepository studentRepository;

    Student student;

    StudentDTO studentDTO;

    private String firstName = "Max";

    @BeforeEach
    void setUp(){
        studentDTO = studentRepository.findStudentById(20000050);
        student = UserFactory.createStudent(studentDTO);
    }

    @Test
    void getId() {
          ;
        assertEquals(20000050, studentDTO.getId());
        assertEquals(20000050, student.getId());
    }

    @Test
    void getW() {
        ;
        assertEquals(firstName, studentDTO.getFirstName());
        assertEquals(firstName, student.getFirstName());
    }


}
