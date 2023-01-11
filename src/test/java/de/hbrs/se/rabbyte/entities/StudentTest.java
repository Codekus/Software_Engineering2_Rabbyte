package de.hbrs.se.rabbyte.entities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    Student student = new Student();

    private static final String FIRST_NAME = "Max";
    private static final String LAST_NAME = "Mustermann";
    private static final String FACULTY = "Informatik";

    @BeforeMethod
    void setUp() {
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setFaculty(FACULTY);
    }

    @Test
    void getFaculty() {
        assertEquals(FACULTY, student.getFaculty());
    }

    @Test
    void getFirstName() {
        assertEquals(FIRST_NAME, student.getFirstName());

    }

    @Test
    void getLastName() {
        assertEquals(LAST_NAME, student.getLastName());

    }
}