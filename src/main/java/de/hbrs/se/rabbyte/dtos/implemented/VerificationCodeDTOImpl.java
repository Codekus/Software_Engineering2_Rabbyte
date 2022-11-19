package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.Person;

import java.util.Date;

public class VerificationCodeDTOImpl implements VerificationCodeDTO {

    private int id;
    private Person person;
    private Date date;
    private String token;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Person getUser() {
        return person;
    }

    public void setUser(Person person) {
        this.person = person;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
