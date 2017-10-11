/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author USUARIO
 */
public class TestCryptographyTripleDES {
    
  public static void main( String[] args ) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException {

   TripleDES cryptography = TripleDES.newInstance();
   String value = "Rodrigo Lazoti";
   System.out.println( "Valor utilizado => " + value );
   String encryptedValue = cryptography.encrypt( value );
   System.out.println( "Valor criptografado => " + encryptedValue );
   String decryptedValue = cryptography.decrypt( encryptedValue );
   System.out.println( "Valor descriptografado => " + decryptedValue );
 }

}