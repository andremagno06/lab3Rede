package Pacote;

import java.io.*;
import java.net.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
   
import javax.crypto.Cipher;

class AES_TCPServer {

    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();

            
            capitalizedSentence = clientSentence.toUpperCase() + '\n';

            outToClient.writeBytes(capitalizedSentence);
            
            System.out.println();
            
            
        }
        
        public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
               Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
               SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
               encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
               return encripta.doFinal(textopuro.getBytes("UTF-8"));
         }
   
        public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
               Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
               SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
               decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
               return new String(decripta.doFinal(textoencriptado),"UTF-8");
         }
         
        
    }
}
