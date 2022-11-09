package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationResultDTOImplTest {

    private RegistrationResultDTOImpl registrationResultDTO;

    private boolean result;
    private List<RegistrationResultDTO.Result> reasons;



    @BeforeEach
    void setUp() {
        registrationResultDTO = new RegistrationResultDTOImpl();
        reasons = new ArrayList<>();
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
}