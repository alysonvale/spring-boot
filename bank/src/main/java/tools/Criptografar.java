package tools;

import java.security.MessageDigest;
import java.time.ZonedDateTime;

public class Criptografar {

    // ESCONDER SENHA COM ALGORITMO - MD5
    public static String gerarHashMD5(String senha) throws Exception {
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
        StringBuilder texto = new StringBuilder();
        for (byte b : hash) {
            texto.append(String.format("%02X", 0xFF & b));
        }
        return texto.toString();
    }

    // TOKEN ALGORITMO - SHA-256
    public static String gerartoken(String login) throws Exception {
        String milisegundos = String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli()) + login;
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(milisegundos.getBytes("UTF-8"));
        StringBuilder texto = new StringBuilder();
        for (byte b : hash) {
            texto.append(String.format("%02X", 0xFF & b));
        }
        return texto.toString();
    }
}
