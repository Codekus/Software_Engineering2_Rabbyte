package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.entities.Student;

import javax.xml.crypto.Data;

public interface ApplicationDTO {


    public JobAdvertisement getJobAdvertisement();

    public Student getStudent();

    public Data getDate();

    public String getApplicationText();
}
