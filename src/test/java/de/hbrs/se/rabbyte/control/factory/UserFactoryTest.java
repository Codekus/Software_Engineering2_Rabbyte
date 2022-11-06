package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserFactoryTest {

    @Mock
    private StudentDTO studentDTO;
    @Mock
    private BusinessDTO businessDTO;
    @Mock
    private User user;

    private final String password = "password";
    private final String emailStudent = "max@mustermann.de";
    private final String studentFirstName = "Max";
    private final String studentLastName = "Mustermann";

    private final String businessMail = "musterman@gmbh.de";
    private final String businessName = "Mustermann Gmbh";
    @Test
    void createStudent()  {
        Student student;
        when(studentDTO.getPassword()).thenReturn(password);
        when(studentDTO.getEmail()).thenReturn(emailStudent);
        when(studentDTO.getFirstName()).thenReturn(studentFirstName);
        when(studentDTO.getLastName()).thenReturn(studentLastName);


        student = UserFactory.createStudent(studentDTO);

        assertTrue(student instanceof Student);
        assertEquals(128 , student.getPassword().length());
        assertEquals(emailStudent , student.getEmail());
        assertEquals(studentFirstName , student.getFirstName());
        assertEquals(studentLastName , student.getLastName());
        assertEquals(128 , student.getSalt().length());

    }

    @Test
    void createBusiness() {
        Business business;

        when(businessDTO.getPassword()).thenReturn(password);
        when(businessDTO.getEmail()).thenReturn(businessMail);
        when(businessDTO.getBusinessName()).thenReturn(businessName);

        business = UserFactory.createBusiness(businessDTO);
        assertEquals(128 , business.getPassword().length());
        assertEquals(businessMail , business.getEmail());
        assertEquals(businessName , business.getBusinessName());
        assertEquals(128 , business.getSalt().length());

    }
}