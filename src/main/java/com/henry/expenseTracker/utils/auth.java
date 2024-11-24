package com.henry.expenseTracker.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class auth {
    public auth() { }

    public static String encryptionPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        try {
            char[] chars = password.toCharArray();
            byte[] salt = System.getenv("SALT").getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, 36000, 64*8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = skf.generateSecret(spec).getEncoded();

            return toHex(hash) ;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new NoSuchAlgorithmException();
        }

    }
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
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
}
