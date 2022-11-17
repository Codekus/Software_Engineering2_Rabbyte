package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.VerificationTokenDTO;
import de.hbrs.se.rabbyte.entities.VerificationToken;

import java.util.Date;
import java.util.UUID;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

public class VerificationFactory {

    public static VerificationToken createVerificationToken(VerificationTokenDTO verificationTokenDTO) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        setUser(verificationTokenDTO.getUser());
        setDate(new Date());
    }
}
