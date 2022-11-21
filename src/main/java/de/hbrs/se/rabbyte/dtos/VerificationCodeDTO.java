package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.Person;

import java.time.LocalDateTime;

public interface VerificationCodeDTO {

    public int getId();
    public Person getPerson();
    public LocalDateTime getDate();
    public String getToken();
}
