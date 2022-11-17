package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.VerificationTokenDTO;
import de.hbrs.se.rabbyte.entities.VerificationToken;

import java.util.Date;
import java.util.UUID;


public class VerificationFactory {

    public static VerificationToken createVerificationToken(VerificationTokenDTO verificationTokenDTO) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(verificationTokenDTO.getUser());
        verificationToken.setDate(new Date());

        return verificationToken;
    }
}
