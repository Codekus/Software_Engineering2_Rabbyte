package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class CryptographyUtilTest {



    private String password = "Password";
    private byte[] arraySalt = new byte[64];
    @Test
    void generateSalt() {
        assertEquals(64 , CryptographyUtil.generateSalt().length);
    }

    @Test
    void hashPassword() throws  InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "Password";
        byte[] arraySalt = new byte[64];
        byte[] hashedPassword = CryptographyUtil.hashPassword(password.toCharArray() , arraySalt);
        assertEquals(
                "TEXCUUrE+64UKyGjM1yumW4Ez9gPJYzMnkOvseWaT57s5a+vfMaYovu7ggMa0DC9cN9JXA0N7YucIgSuDEnrYQ=="
                , Base64.getEncoder().encodeToString(hashedPassword));;

    }

    @Test
    void encryptPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertEquals(128  , CryptographyUtil.encryptPassword(password, arraySalt).length());
    }
}