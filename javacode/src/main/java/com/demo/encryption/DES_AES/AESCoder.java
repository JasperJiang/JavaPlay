package com.demo.encryption.DES_AES;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class AESCoder {
    private static final String IV = "AAAAAAAAAAAAAAAA";

    /*
     * AES 128
     */
    public static String encryptAES128(String text, String key) {
        try {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(hashMD5(key), "AES");
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);

            return Base64.getEncoder().encodeToString(mCipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptAES128(String text, String key) {
        try {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(hashMD5(key), "AES");
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);

            return new String(mCipher.doFinal(Base64.getDecoder().decode(text)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] hashMD5(String input) {
        byte[] bytesInput = input.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytesInput);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String or_message = "abcdefg";
        String key = "123213123";

        String encry_message = encryptAES128(or_message,"123213123");

        System.out.println(decryptAES128(encry_message,key));
    }
}
