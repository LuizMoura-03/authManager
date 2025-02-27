package com.zup.jwt_authManager.infra.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtTokenProvider {
    private String jwtSecret = "caf60addca9ea3e3c099551e1b6576c9966dce0a33de879dd7e160f86dbd872ca"; // Chave secreta para assinar o token.
    private long jwtExpirationDate = 300000; // Tempo de expiração do token (5 minutos).

    public String generateToken(String username, Map<String, Object> claims) {   // Gera um token JWT com roles e claims
        Date currentDate = new Date(); // Data atual.
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate); // Data de expiração.

        return Jwts.builder()
                .setSubject(username) // Define o nome do usuário como o assunto do token.
                .addClaims(claims) // Adiciona claims.
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

    // Extrai uma claim especifica do token JWT.
    public Object getClaim(String token, String claimKey) {
        return Jwts.parserBuilder()
                .setSigningKey(key()) // Define a chave para validação.
                .build()
                .parseClaimsJws(token) // Analisa o token.
                .getBody()
                .get(claimKey); // Retorna a claim específica.
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

