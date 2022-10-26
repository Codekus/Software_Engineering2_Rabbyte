package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;

import javax.xml.crypto.Data;

public class ApplicationDTOImpl implements ApplicationDTO {

    private JobAdvertisement jobAdvertisement;
    private Student student;
    private Data date;
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

    public Data getDate() {
        return date;
    }

    public void setDate(Data date) {
        this.date = date;
    }

    public String getApplicationText() {
        return applicationText;
    }

    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }
}
