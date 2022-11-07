package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.dtos.implemented.RegistrationResultDTOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationResultDTOTest {


    @Test
    void registrationResultEnum() {
        assertEquals("EMAIL_IN_USE" , RegistrationResultDTO.Result.EMAIL_IN_USE.toString());
        assertEquals("INVALID_EMAIL" , RegistrationResultDTO.Result.INVALID_EMAIL.toString());
        assertEquals("PASSWORD_TO_SHORT" , RegistrationResultDTO.Result.PASSWORD_TO_SHORT.toString());
        assertEquals("PASSWORD_REPEAT_TO_SHORT" , RegistrationResultDTO.Result.PASSWORD_REPEAT_TO_SHORT.toString());
        assertEquals("INVALID_FIRST_NAME" , RegistrationResultDTO.Result.INVALID_FIRST_NAME.toString());
        assertEquals("INVALID_LAST_NAME" , RegistrationResultDTO.Result.INVALID_LAST_NAME.toString());
        assertEquals("INVALID_BUSINESS_NAME" , RegistrationResultDTO.Result.INVALID_BUSINESS_NAME.toString());
        assertEquals("BUSINESS_NAME_IN_USE" , RegistrationResultDTO.Result.BUSINESS_NAME_IN_USE.toString());
        assertEquals("PASSWORD_TOO_COMMON" , RegistrationResultDTO.Result.PASSWORD_TOO_COMMON.toString());
        assertEquals("GENERAL_ERROR" , RegistrationResultDTO.Result.GENERAL_ERROR.toString());
        assertEquals("PASSWORD_DIFFERENT" , RegistrationResultDTO.Result.PASSWORD_DIFFERENT.toString());

    }
}