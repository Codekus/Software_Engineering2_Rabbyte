package de.hbrs.se.rabbyte.util;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public  class CryptographyUtil {

    private static final int HASH_BYTE_SIZE = 64; // 512 bits
    private static final int PBKDF2_ITERATIONS = 10000;

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[HASH_BYTE_SIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] hashPassword( final char[] password, final byte[] salt ) {

        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static String encryptPassword(String plainPassword, final byte[] salt) throws NoSuchAlgorithmException {

        return toHex(hashPassword(plainPassword.toCharArray() , salt));


    }

    public static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
