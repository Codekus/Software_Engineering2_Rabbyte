package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;

import java.time.LocalDate;

/**
 * A DTO for the {@link de.hbrs.se.rabbyte.entities.Application} entity
 */
public class ApplicationDTOImpl implements ApplicationDTO {

    private JobAdvertisement jobAdvertisement;
    private Student student;
    private LocalDate date;
    private String applicationText;

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getApplicationText() {
        return applicationText;
    }

    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }
}
