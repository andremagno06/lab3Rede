package pacote;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;

public class AES {

    static String IV = "AAAAAAAAAAAAAAAA";

    public AES() {
    }
/*
    public static void main(String[] args) {

        try {

            System.out.println("Texto Puro: " + textopuro);

            byte[] textoencriptado = criptografa(textopuro, chaveencriptacao);

            System.out.print("Texto Encriptado: ");

            for (int i = 0; i < textoencriptado.length; i++) {
                System.out.print(new Integer(textoencriptado[i]) + " ");
            }

            System.out.println("");

            String textodecriptado = descriptografa(textoencriptado, chaveencriptacao);

            System.out.println("Texto Decriptado: " + textodecriptado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public  byte[] criptografa(String textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }

    public  String descriptografa(byte[] textoencriptado, String chaveencriptacao) throws Exception {
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(textoencriptado), "UTF-8");
    }

    public String fromHex(byte[] hex) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hex.length; i++) {
            sb.append(Integer.toString((hex[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public byte[] toHex(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}
