package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDTOImplTest {

    StudentDTOImpl studentDTO = new StudentDTOImpl();

    private String firstName = "Max";
    private String lastName = "Mustermann";
    private String faculty = "Informatik";

    @BeforeEach
    void setUp() {
        studentDTO.setFirstName(firstName);
        studentDTO.setLastName(lastName);
        studentDTO.setFaculty(faculty);
    }

    @Test
    void getFaculty() {
        assertEquals(faculty,studentDTO.getFaculty());
    }

    @Test
    void getFirstName() {
        assertEquals(firstName , studentDTO.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(lastName , studentDTO.getLastName());
    }
}