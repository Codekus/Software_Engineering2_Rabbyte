package de.hbrs.se.rabbyte.util;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;



public  class CryptographyUtil {


    private CryptographyUtil() {
        throw new IllegalStateException(Globals.StateException.MESSAGE_UTILS);
    }
    private static final int HASH_BYTE_SIZE = 64; // 512 bits
    private static final int PBKDF2_ITERATIONS = 120000;

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[HASH_BYTE_SIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] hashPassword( final char[] password, final byte[] salt ) throws InvalidKeySpecException, NoSuchAlgorithmException {


            PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return  skf.generateSecret(spec).getEncoded();

    }

    public static String encryptPassword(String plainPassword, final byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return toHex(hashPassword(plainPassword.toCharArray() , salt));


    }

    public static String toHex(byte[] array)
    {
        return DatatypeConverter.printHexBinary(array);
    }

    public static byte[] fromHex(String hex)
    {
        byte[] byteArray = new BigInteger(hex, 16)
                .toByteArray();
        if (byteArray[0] == 0) {
            byte[] output = new byte[byteArray.length - 1];
            System.arraycopy(
                    byteArray, 1, output,
                    0, output.length);
            return output;
        }
        return byteArray;
    }
}
