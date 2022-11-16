package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class CryptographyUtilTest {

    private static final String error = "class de.hbrs.se.rabbyte.util.CryptographyUtilTest cannot access a member of class de.hbrs.se.rabbyte.util.CryptographyUtil with modifiers \"private\"";


    private static final String password = "Password";
    private final byte[] arraySalt =  new byte[64];
    @Test
    void generateSalt() {
        assertEquals(64 , CryptographyUtil.generateSalt().length);
    }

    @Test
    void hashPassword() throws  InvalidKeySpecException, NoSuchAlgorithmException {

        byte[] hashedPassword = CryptographyUtil.hashPassword(password.toCharArray() , arraySalt);
        assertEquals(
                "EXzc11qKyYLTWF/ujw0m/3lxnScZUZ1xrWxvPxxlT9AESueP3DwmqTevv5Nl2Py+X5FpCgnEFusiZsM3BdMh6Q=="
                , Base64.getEncoder().encodeToString(hashedPassword));
    }

    @Test
    void encryptPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertEquals(128  , CryptographyUtil.encryptPassword(password, arraySalt).length());
    }

    @Test
    void ThrowIllegalAccesExceptionWhenInstancingCryptographyUtil() throws NoSuchMethodException {
        Constructor<CryptographyUtil> constructor = CryptographyUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(error, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);

    }

    @Test
    void fromHex() {
        byte[] byteArray = CryptographyUtil.fromHex("FFFF");
        assertEquals(1 , CryptographyUtil.fromHex("FF").length);
        assertEquals(2 , CryptographyUtil.fromHex("FFFF").length);
        assertEquals( Base64.getEncoder().encodeToString(byteArray) , Base64.getEncoder().encodeToString(CryptographyUtil.fromHex("FFFF")));

    }

    @Test
    void conversionTest() {
        byte[] byteArray = CryptographyUtil.generateSalt();
        String hex = CryptographyUtil.toHex(byteArray);
        assertArrayEquals(byteArray , CryptographyUtil.fromHex(hex));
    }
}