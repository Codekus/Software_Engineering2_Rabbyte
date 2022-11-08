package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationDTOImplTest {

    private RegistrationDTOImpl registrationDTO = new RegistrationDTOImpl();
    private String repeatPassword;
    @AfterEach
    void tearDown() {
        repeatPassword = "password";
        registrationDTO.setRepeatPassword(repeatPassword);
    }
    @Test
    void getPassword() {
        assertEquals(repeatPassword , registrationDTO.getRepeatPassword());
    }
}