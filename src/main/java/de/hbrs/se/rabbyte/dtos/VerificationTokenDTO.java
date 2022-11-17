package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.User;

import java.util.Date;

public interface VerificationTokenDTO {
    public int getVerificationId();
    public User getUser();
    public Date getDate();
    public String getToken();
}
