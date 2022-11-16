package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    Student student = new Student();

    private final String firstName = "Max";
    private final String lastName = "Mustermann";
    private final String faculty = "Informatik";

    @BeforeEach
    void setUp() {
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setFaculty(faculty);
    }

    @Test
    void getFaculty() {
        assertEquals(faculty , student.getFaculty());
    }

    @Test
    void getFirstName() {
        assertEquals(firstName , student.getFirstName());

    }

    @Test
    void getLastName() {
        assertEquals(lastName , student.getLastName());

    }
}