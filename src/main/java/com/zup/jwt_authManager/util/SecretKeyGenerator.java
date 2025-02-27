package com.zup.jwt_authManager.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) throws Exception {

        try {
            // Cria uma instancia do KeyGenerator para o algoritmo HmacSHA256
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");

            // Inicializa o KeyGenerator com o tamanho da chave (256 bits)
            keyGen.init(256);

            // Gera a chave secreta
            SecretKey secretKey = keyGen.generateKey();

            // Codifica a chave em Base64
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            // Exibe a chave gerada
            System.out.println("Chave secreta gerada: " + encodedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
