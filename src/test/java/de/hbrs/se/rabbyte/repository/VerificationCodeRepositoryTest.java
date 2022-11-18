package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

class VerificationCodeRepositoryTest {


    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Test
    void findVerificationCodeByToken() {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findByToken("606728a3-f4dd-4a12-a75d-1411773e25b7");
        assertEquals(100 , verificationCodeDTO.getId());
    }
    @Test
    void findVerificationCodeById() {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findByVerificationId(60000017);
        assertEquals(100 , verificationCodeDTO.getId());
    }


}