package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.VerificationTokenDTO;
import de.hbrs.se.rabbyte.entities.User;

import java.util.Date;

public class VerificationTokerDTOImpl implements VerificationTokenDTO {

    private int id;
    private User user;
    private Date date;
    private String token;

    public int getVerificationId() {
        return id;
    }

    public void setVerificationId(int id) {
        this.id = id;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
