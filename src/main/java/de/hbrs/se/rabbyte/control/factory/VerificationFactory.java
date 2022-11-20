package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.VerificationCode;

import java.util.Date;
import java.util.UUID;


public class VerificationFactory {

    public static VerificationCode createVerificationToken(Student student) {

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setId(verificationCode.getId());
        verificationCode.setToken(UUID.randomUUID().toString());
        verificationCode.setPerson(student);
        verificationCode.setDate(new Date());

        return verificationCode;
    }
}
