package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationDTOImplTest {

    private final ApplicationDTOImpl applicationDTO = new ApplicationDTOImpl();
    @Mock
    private JobAdvertisement jobAdvertisement;
    @Mock
    private Student student;
    public LocalDate date;
    private final String applicationText = "Application Text";

    @BeforeEach
    void setUp() {
        date = LocalDate.of(1990 , 1,20);
        applicationDTO.setDate(date);
        jobAdvertisement = new JobAdvertisement();
        applicationDTO.setJobAdvertisement(jobAdvertisement);
        student = new Student();
        applicationDTO.setStudent(student);
        applicationDTO.setApplicationText(applicationText);
    }

    @Test
    void getJobAdvertisement() {
        assertTrue( applicationDTO.getJobAdvertisement() instanceof JobAdvertisement);
    }

    @Test
    void getStudent() {
        assertTrue( applicationDTO.getStudent() instanceof  Student);
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(1990 , 1 , 20) , applicationDTO.getDate());
    }

    @Test
    void getApplicationText() {
        assertEquals(applicationText , applicationDTO.getApplicationText());
    }
}