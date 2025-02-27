package com.zup.jwt_authManager.infra.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {
    private String jwtSecret = "caf60addca9ea3e3c099551e1b6576c9966dce0a33de879dd7e160f86dbd872ca"; // Chave secreta para assinar o token.
    private long jwtExpirationDate = 300000; // Tempo de expiração do token (5 minutos).

    public String generateToken(String username) {
        // Gera um token JWT
        Date currentDate = new Date(); // Data atual.
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate); // Data de expiração.

        return Jwts.builder()
                .setSubject(username) // Define o nome do usuário como o assunto do token.
                .setIssuedAt(currentDate) // Define a data de emissão.
                .setExpiration(expireDate) // Define a data de expiração.
                .signWith(SignatureAlgorithm.HS256, key()) // Assina o token com a chave secreta.
                .compact(); // Retorna o token gerado.
    }

    private Key key() {
        // Converte a chave secreta em um objeto Key.
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        // Extrai o nome do usuário do token JWT.
        return Jwts.parserBuilder()
                .setSigningKey(key()) // Define a chave para validação.
                .build()
                .parseClaimsJws(token) // Analisa o token.
                .getBody()
                .getSubject(); // Retorna o nome do usuário.
    }

    public boolean validateToken(String token) {
        // Valida o token JWT.
        Jwts.parserBuilder()
                .setSigningKey(key()) // Define a chave para validação.
                .build()
                .parseClaimsJws(token); // Analisa o token.
        return true; // Retorna true se o token for válido.
    }
}

