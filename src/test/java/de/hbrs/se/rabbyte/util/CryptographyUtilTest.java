package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class CryptographyUtilTest {

    CryptographyUtil cryptographyUtil = new CryptographyUtil();

    private String password = "Password";
    private byte[] arraySalt = new byte[64];
    @Test
    void generateSalt() {

        assertTrue(cryptographyUtil.generateSalt().length == 64);
    }

    @Test
    void hashPassword() {
        String password = "Password";
        byte[] arraySalt = new byte[64];
        byte[] hashedPassword = cryptographyUtil.hashPassword(password.toCharArray() , arraySalt);

        assertEquals(
                "42DNDeYe/9Vudnh9lMKlPvfgiN1IThX8M2ykKjWcCHT9B8kQqZbmg1uhnidHFjdlYzFMdBwDYpJkCszQsHWOCg=="
                , Base64.getEncoder().encodeToString(hashedPassword));;

    }

    @Test
    void encryptPassword() throws NoSuchAlgorithmException {
        assertEquals(cryptographyUtil.encryptPassword(password, arraySalt).length()  , 128);
    }
}