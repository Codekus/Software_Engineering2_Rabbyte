package de.hbrs.se.rabbyte.entities;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

class ApplicationTest {
    private final Application application = new Application();

    private static final String APPLICATION_TEXT = "Application Text";

    @Mock
    JobAdvertisement jobAdvertisement;
    @Mock
    Student student;
    @BeforeMethod
    void setUp() {
        jobAdvertisement = new JobAdvertisement();
        student = new Student();
        application.setApplicationText("Application Text");
        application.setId(200);
        application.setJobAdvertisement(jobAdvertisement);
        application.setStudent(student);
        application.setDate(LocalDate.of(2000,1,20));

    }

    @Test
    void getId() {
        assertEquals(200 , application.getId());
    }


    @Test
    void getJobAdvertisement() {
        assertNotNull(application.getJobAdvertisement());
        assertTrue(application.getJobAdvertisement() instanceof  JobAdvertisement);
    }



    @Test
    void getStudent() {
        assertNotNull(application.getStudent());
        assertTrue(application.getStudent() instanceof  Student);
    }



    @Test
    void getDate() {
        assertEquals(LocalDate.of(2000 , 1 , 20) , application.getDate());
    }



    @Test
    void getApplicationText() {
        assertEquals(APPLICATION_TEXT, application.getApplicationText());
    }


}