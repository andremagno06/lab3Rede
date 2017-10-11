package pacote;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import pacote.AES;

class AES_TCPServer {

    public AES_TCPServer() {
    }

    public static void main(String argv[]) throws Exception {
        
        String chave= "9123456789abcdef";
        String clientSentence;
        String msgMaiusculaCriptografada;
        AES aes = new AES();

        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("SERVIDOR INICIADO");
        while (true) {

         
            
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());


            //Recebeui
            clientSentence = inFromClient.readLine();
            String  msgRecebida = clientSentence;
            System.out.println("Servidor Recebeu: " + msgRecebida+'\n');
            
            //Processamento descriptografia
             String msgDescriptografada = aes.descriptografa(aes.toHex(msgRecebida), chave);
            System.out.println("Msg Descriptografada: " + msgDescriptografada);
            
            //Processamento maiusculo
            
           String msgToMausculo= msgDescriptografada.toUpperCase();
            System.out.println("Mesagem envida enviada para o cliente antes de criptografia: "+ msgToMausculo);
            
            
              //Processamento criptografia
            msgMaiusculaCriptografada= aes.fromHex( aes.criptografa(msgToMausculo, chave));
     
            
            //envia mensagem para o cliente criptografado 
            outToClient.writeBytes(msgMaiusculaCriptografada.toUpperCase() +'\n');
         
            System.out.println("Mesagem criptografada enviada para o cliente: "+ msgMaiusculaCriptografada);

        }
        }

    }

