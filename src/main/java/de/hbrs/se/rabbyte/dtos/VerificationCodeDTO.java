package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.Person;

import java.util.Date;

public interface VerificationCodeDTO {

    public int getId();
    public Person getUser();
    public Date getDate();
    public String getToken();
}
