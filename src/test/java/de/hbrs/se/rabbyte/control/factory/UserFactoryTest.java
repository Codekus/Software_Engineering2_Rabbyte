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

    @Test
    void createStudent() throws NoSuchAlgorithmException {
        Student student;
        when(studentDTO.getPassword()).thenReturn("password");
        when(studentDTO.getEmail()).thenReturn("max@mustermann.de");
        when(studentDTO.getFirstName()).thenReturn("Max");
        when(studentDTO.getLastName()).thenReturn("Mustermann");


        student = UserFactory.createStudent(studentDTO);

        assertTrue(student instanceof Student);
        assertEquals(128 , student.getPassword().length());
        assertEquals("max@mustermann.de" , student.getEmail());
        assertEquals("Max" , student.getFirstName());
        assertEquals("Mustermann" , student.getLastName());
        assertEquals(128 , student.getSalt().length());

    }

    @Test
    void createBusiness() {
        Business business;
        String password = "password";
        when(businessDTO.getPassword()).thenReturn(password);
        when(businessDTO.getEmail()).thenReturn("musterman@gmbh.de");
        when(businessDTO.getBusinessName()).thenReturn("Mustermann Gmbh");

        business = UserFactory.createBusiness(businessDTO);
        assertEquals(128 , business.getPassword().length());
        assertEquals("musterman@gmbh.de" , business.getEmail());
        assertEquals("Mustermann Gmbh" , business.getBusinessName());
        assertEquals(128 , business.getSalt().length());

    }
}