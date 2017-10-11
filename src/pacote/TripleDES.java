package pacote;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;
import java.util.UUID;
import java.io.UnsupportedEncodingException;




/**
 * Class that supplies a criptography of triple type DES.
 * @author Rodrigo Lazoti
 * @since 05/07/2009
 */
public class TripleDES {
   private Cipher cipher;
   private byte[] encryptKey;
   private KeySpec keySpec;
   private SecretKeyFactory secretKeyFactory;
   private SecretKey secretKey;

 
   public static TripleDES newInstance() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
     return new TripleDES();
   }

   private TripleDES() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    
     String key = "12dsdvsdvs¨345@sdxdfcbfxfdcv";
     encryptKey = key.getBytes( "UTF-8" );
     cipher = Cipher.getInstance( "DESede" );
     keySpec = new DESedeKeySpec(encryptKey );
     secretKeyFactory = SecretKeyFactory.getInstance( "DESede" );
     secretKey = secretKeyFactory.generateSecret( keySpec );
   }

   public String encrypt( String value ) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
     cipher.init( Cipher.ENCRYPT_MODE, secretKey );
     return Base64.getEncoder().encodeToString(cipher.doFinal( value.getBytes( "UTF-8" ) ));
   }

   public String decrypt( String value ) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
     cipher.init( Cipher.DECRYPT_MODE, secretKey );
     byte[] decipherText = cipher.doFinal( Base64.getDecoder().decode(value) );
     return new String( decipherText );
   }

}