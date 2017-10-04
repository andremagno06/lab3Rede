package rede
        ;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;

class TCPClient1_MD5 {

    public static void main(String argv[]) throws Exception {
        while (true) {
            String sentence;
            String modifiedSentence;
            System.out.println("------------------------");
            System.out.println("Cliente SHA1");

            System.out.println("Digite Mensagem: ");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket("172.18.131.181", 6789);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sentence.getBytes());
            byte[] hashMd5 = md.digest();

            String hash = stringHexa(hashMd5);

            outToServer.writeBytes(sentence + ";" + hash + '\n');
            System.out.println("Enviou: " + sentence + ";" + hash);

            //recebe mendagem do servidor
            modifiedSentence = inFromServer.readLine();
            System.out.println("Recebi: " + modifiedSentence);
            
            //separa as mensagem;
            String[] mensagem;
            mensagem = modifiedSentence.split(";");

            //Gera o Hash apartir da mensagem enviada
            MessageDigest md2 = MessageDigest.getInstance("MD5");
            md.update(mensagem[0].getBytes());
            byte[] hashMd5_2 = md.digest();

            System.out.println("Mensagem: " + mensagem[0]);
            System.out.println("hash enviado pelo Servidor: " + mensagem[1]);
            System.out.println("hash Gerado pelo Cliente: " + stringHexa(hashMd5_2));

            //compara hash recebido e hash gerado
            if (mensagem[1].equals(stringHexa(hashMd5_2))) {
                System.out.println("Mesagem Autenticada");
            } else {
                System.out.println("Mesagem Não Autenticada");

            }

            clientSocket.close();
        }
    }

    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}
