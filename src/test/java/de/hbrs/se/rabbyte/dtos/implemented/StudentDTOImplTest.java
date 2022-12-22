package de.hbrs.se.rabbyte.dtos.implemented;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDTOImplTest {

    StudentDTOImpl studentDTO = new StudentDTOImpl();

    private static final String FIRST_NAME = "Max";
    private static final String LAST_NAME = "Mustermann";
    private static final String FACULTY = "Informatik";

    @BeforeMethod
    void setUp() {
        studentDTO.setFirstName(FIRST_NAME);
        studentDTO.setLastName(LAST_NAME);
        studentDTO.setFaculty(FACULTY);
    }

    @Test
    void getFaculty() {
        assertEquals(FACULTY,studentDTO.getFaculty());
    }

    @Test
    void getFirstName() {
        assertEquals(FIRST_NAME, studentDTO.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(LAST_NAME, studentDTO.getLastName());
    }
}