/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decryptbpjs;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author dhias
 */
public class Enc {
        public static final String ALGORITHM = "AES/CBC/PKCS5Padding";

        public static AesKeySpec generateKey(String key) 
                    throws NoSuchPaddingException, NoSuchAlgorithmException,
                    InvalidAlgorithmParameterException, InvalidKeyException,
                    BadPaddingException, IllegalBlockSizeException {

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] _hashKey = digest.digest(key.getBytes(StandardCharsets.UTF_8));
                byte[] _hashIv = new byte[16];
                for (int i = 0; i < 16; i++) {
                        _hashIv[i] = _hashKey[i];
                }

                AesKeySpec aesKeySpec = new AesKeySpec();
                SecretKeySpec _key = new SecretKeySpec(_hashKey, "AES");
                IvParameterSpec _iv = new IvParameterSpec(_hashIv);
                aesKeySpec.setKey(_key);
                aesKeySpec.setIv(_iv);
                return aesKeySpec;
        }

        public static String decrypt(String cipherText, SecretKeySpec key, IvParameterSpec iv) 
                        throws NoSuchPaddingException, NoSuchAlgorithmException,
                    InvalidAlgorithmParameterException, InvalidKeyException,
                    BadPaddingException, IllegalBlockSizeException {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(plainText);
        }
        
        public static String encrypt(String cipherText, SecretKeySpec key, IvParameterSpec iv) 
                        throws NoSuchPaddingException, NoSuchAlgorithmException,
                    InvalidAlgorithmParameterException, InvalidKeyException,
                    BadPaddingException, IllegalBlockSizeException {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            //byte[] plainText = cipher.doFinal(Base64.getEncoder().e(cipherText));
            //return new String(plainText);
            return Base64.getEncoder().encodeToString(cipher.doFinal(cipherText.getBytes(StandardCharsets.UTF_8)));
        }        

    //String respon = LZString.decompressFromEncodedURIComponent(lzString);
}
