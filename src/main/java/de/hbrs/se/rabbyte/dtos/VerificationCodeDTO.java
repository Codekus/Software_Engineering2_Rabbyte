package de.hbrs.se.rabbyte.dtos;

import java.util.Date;

public interface VerificationCodeDTO {

    public int getVerificationId();
    public GeneralUserDTO getUser();
    public Date getDate();
    public String getToken();
}
