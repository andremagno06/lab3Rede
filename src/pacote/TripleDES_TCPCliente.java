package pacote;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static pacote.AES.IV;

class TripleDES_TCPCliente {

    public static void main(String argv[]) throws Exception {
        
        
        
        String msgMesagemEnviada;
        String modifiedSentence;
        TripleDES tdes = TripleDES.newInstance();
        
        System.out.println("CLIENTE INICIADO");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //enviada sentença
        msgMesagemEnviada = inFromUser.readLine();
        System.out.println("Mesagem escrita : " + msgMesagemEnviada);

        //criptografa a mensagemm digitada
        String msgCriptografada =tdes.encrypt(msgMesagemEnviada);
        System.out.println("Mesagem criptografada:"+msgCriptografada);
        
        //eniva mensagem para o servidor criptografada
        outToServer.writeBytes(msgCriptografada + '\n');

        //recebe mensagem do servidor
        modifiedSentence = inFromServer.readLine();
        System.out.println("Mesagem Recebida: "+modifiedSentence);
        //descriptografa mensagem vinda so servidor
        String msgDescriptografada = tdes.decrypt(modifiedSentence);

        System.out.println("Msg Descriptografada: " + msgDescriptografada);

        clientSocket.close();

    }

}
