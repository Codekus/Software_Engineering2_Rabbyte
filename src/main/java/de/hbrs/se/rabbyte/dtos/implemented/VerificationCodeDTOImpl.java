package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;

import java.util.Date;

public class VerificationCodeDTOImpl implements VerificationCodeDTO {

    private int id;
    private GeneralUserDTO user;
    private Date date;
    private String token;

    public int getVerificationId() {
        return id;
    }

    public void setVerificationId(int id) {
        this.id = id;
    }

    @Override
    public GeneralUserDTO getUser() {
        return user;
    }

    public void setUser(GeneralUserDTO user) {
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
