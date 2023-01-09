package de.hbrs.se.rabbyte.dtos;

import java.util.List;

public interface RegistrationResultDTO {

    enum Result {
        EMAIL_IN_USE,
        INVALID_EMAIL,
        PASSWORD_TO_SHORT,
        PASSWORD_REPEAT_TO_SHORT,
        PASSWORD_DIFFERENT,
        INVALID_FIRST_NAME,
        INVALID_LAST_NAME,
        INVALID_BUSINESS_NAME,
        BUSINESS_NAME_IN_USE,
        PASSWORD_TOO_COMMON,
        GENERAL_ERROR,
        DATABASE_USER_EXCEPTION;

    }
    public void setRegistrationResult(boolean result);
    public boolean getRegistrationResult();

    public void setReason(Result reason);

    public List<Result> getReasons();
}
