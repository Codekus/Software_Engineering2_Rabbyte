package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificationResultDTOImplTest {

    VerificationResultDTOImpl verificationResultDTO;

    @BeforeEach
    void setUp() {
         verificationResultDTO = new VerificationResultDTOImpl();
         verificationResultDTO.setActivationResult(false);
    }

    @Test
    void getActivationResult() {
        assertFalse(verificationResultDTO.getActivationResult());
        verificationResultDTO.setActivationResult(true);
        assertTrue(verificationResultDTO.getActivationResult());
    }
}