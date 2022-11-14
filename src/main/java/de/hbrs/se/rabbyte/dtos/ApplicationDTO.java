package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;

import java.time.LocalDate;

public interface ApplicationDTO {


    public JobAdvertisement getJobAdvertisement();

    public Student getStudent();

    public LocalDate getDate();

    public String getApplicationText();
}
