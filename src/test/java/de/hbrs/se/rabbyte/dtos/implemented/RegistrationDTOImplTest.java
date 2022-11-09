package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationDTOImplTest {

    private RegistrationDTOImpl registrationDTO = new RegistrationDTOImpl();
    private String registrationRepeatPw = "12345";
    @AfterEach
    void tearDown() {
        registrationDTO.setRepeatPassword(registrationRepeatPw);
    }
    @Test
    void getPassword() {
        assertEquals(registrationRepeatPw , registrationDTO.getRepeatPassword());
    }
}