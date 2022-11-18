package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.User;

import java.util.Date;

public interface VerificationCodeDTO {

    public int getId();
    public User getUser();
    public Date getDate();
    public String getToken();
}
