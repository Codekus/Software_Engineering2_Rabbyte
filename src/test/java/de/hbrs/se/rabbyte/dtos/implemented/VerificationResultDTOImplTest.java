package de.hbrs.se.rabbyte.dtos.implemented;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VerificationResultDTOImplTest {

    VerificationResultDTOImpl verificationResultDTO;

    @BeforeMethod
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