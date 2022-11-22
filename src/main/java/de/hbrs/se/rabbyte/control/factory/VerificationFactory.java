package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.VerificationCode;

import java.time.LocalDateTime;
import java.util.UUID;


public class VerificationFactory {

    public static VerificationCode createVerificationToken(Person person) {

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setId(verificationCode.getId());
        verificationCode.setToken(UUID.randomUUID().toString());
        verificationCode.setPerson(person);
        verificationCode.setDate(LocalDateTime.now());

        return verificationCode;
    }

    public static VerificationCode updateVerificationCode(VerificationCodeDTO verificationCodeDTO) {

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setId(verificationCodeDTO.getId());
        verificationCode.setToken(UUID.randomUUID().toString());
        verificationCode.setPerson(verificationCodeDTO.getPerson());
        verificationCode.setDate(LocalDateTime.now());

        return verificationCode;
    }
}
