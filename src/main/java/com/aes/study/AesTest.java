package com.aes.study;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Lingfeng on 2016/7/15.
 */
public class AesTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "陈奕迅";
        System.out.println("加密前: " + content);

        String key = "eason";
        System.out.println("密钥: " + key);

        String encrypt = aesEncrypt(content, key);
        System.out.println("加密后: " + encrypt);

        String decrypt = aesDecrypt(encrypt, key);
        System.out.println("解密后: " + decrypt);
    }

    public static String aesEncrypt(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); // create Random Number Generator(RNG)
            random.setSeed(key.getBytes());
            kgen.init(128, random);

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String aesDecrypt(String encryptStr, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); // create Random Number Generator(RNG)
            random.setSeed(key.getBytes());
            kgen.init(128, random);

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
