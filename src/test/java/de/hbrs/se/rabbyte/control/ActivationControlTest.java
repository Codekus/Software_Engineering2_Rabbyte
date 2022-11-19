package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ActivationControlTest {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Test
    void activate() {
    }

    @Test
    void getVerificationCode() {

        assertEquals(200 , verificationCodeRepository.findVerificationCodeById(60000018));

    }
}