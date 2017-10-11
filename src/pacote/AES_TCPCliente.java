package pacote;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static pacote.AES.IV;

class AES_TCPClient {

    public static void main(String argv[]) throws Exception {
        
        
        String chave= "9123456789abcdef";
        String msgMesagemEnviada;
        String modifiedSentence;
        AES aes = new AES();
        System.out.println("CLIENTE INICIADO");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //enviada sentença
        msgMesagemEnviada = inFromUser.readLine();
        System.out.println("Mesagem escrita : " + msgMesagemEnviada);

        //criptografa a mensagemm digitada
        String msgCriptografada = aes.fromHex(aes.criptografa(msgMesagemEnviada, chave));
        System.out.println("Mesagem criptografada:"+msgCriptografada);
        
        //eniva mensagem para o servidor criptografada
        outToServer.writeBytes(msgCriptografada + '\n');

        //recebe mensagem do servidor
        modifiedSentence = inFromServer.readLine();
        System.out.println("Mesagem Recebida: "+modifiedSentence);
        //descriptografa mensagem vinda so servidor
        String msgDescriptografada = aes.descriptografa(aes.toHex(modifiedSentence),chave);

        System.out.println("Msg Descriptografada: " + msgDescriptografada);

        clientSocket.close();

    }

}
