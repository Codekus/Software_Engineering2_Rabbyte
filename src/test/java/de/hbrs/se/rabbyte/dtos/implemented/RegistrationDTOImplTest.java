package de.hbrs.se.rabbyte.dtos.implemented;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationDTOImplTest {

    private final RegistrationDTOImpl registrationDTO = new RegistrationDTOImpl();
    private static final String REGISTRATION_REPEAT_PW = "12345";
    @BeforeEach
    void setup() {
        registrationDTO.setRepeatPassword(REGISTRATION_REPEAT_PW);
    }
    @Test
    void getPassword() {
        assertEquals(REGISTRATION_REPEAT_PW, registrationDTO.getRepeatPassword());
    }
}