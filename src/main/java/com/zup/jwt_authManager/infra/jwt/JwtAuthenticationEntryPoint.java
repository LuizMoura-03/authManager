package com.zup.jwt_authManager.infra.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {
        // Define o codigo de status HTTP como 401 (Não autorizado) e envia uma mensagem de erro.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso negado. Você deve estar autenticado para acessar este recurso.");
    }
}
