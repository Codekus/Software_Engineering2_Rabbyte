package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationResultDTOImplTest {

    private RegistrationResultDTOImpl registrationResultDTO;

    private boolean result;



    @BeforeEach
    void setUp() {
        registrationResultDTO = new RegistrationResultDTOImpl();
        result = true;
        registrationResultDTO.setRegistrationResult(result);

    }

    @Test
    void getRegistrationResult() {
        assertTrue(registrationResultDTO.getReasons().isEmpty());
        registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
        assertFalse(registrationResultDTO.getReasons().isEmpty());
    }

    @Test
    void getReasons() {
        assertTrue(registrationResultDTO.getRegistrationResult());
    }

    @Test
    void emptyReason() {
        assertTrue(registrationResultDTO.getReasons().isEmpty());
        registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
        assertFalse(registrationResultDTO.getReasons().isEmpty());
    }
}