/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decryptbpjs;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author dhias
 */

public class DecryptBPJS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // TODO code application logic here

        String clearText = "oke, berhasil men-decrypt";
                
        //generate key dari consid+secret+timestamp saat ngirim request
        AesKeySpec mykey = Enc.generateKey("aaaaabbbbbbbbbb1629762146");
        
        // proses encrypt
        String compressedText = LZString.compressToEncodedURIComponent(clearText);        
        // mengcompress
        String encryptedString = Enc.encrypt(compressedText, mykey.getKey(), mykey.getIv());
        
        // proses decrypt
        String decryptedString = Enc.decrypt(encryptedString, mykey.getKey(), mykey.getIv());
        //kemudian di decompress
        String decompressedStrig = LZString.decompressFromEncodedURIComponent(decryptedString);
        
        System.out.println("encrypted: " + encryptedString);
        System.out.println("decrypted: " + decryptedString);
        System.out.println("decompressed: " + decompressedStrig);
        
    }
    
}
