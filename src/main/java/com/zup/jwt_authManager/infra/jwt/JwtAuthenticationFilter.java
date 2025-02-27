package com.zup.jwt_authManager.infra.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }


    // Este método é executado para cada solicitação interceptada pelo filtro.
    //E extrai o token do cabeçalho da solicitação e valida o token.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Obtem o token JWT do cabeçalho da requisição.
        String token = getTokenFromRequest(request);

        // Valida o token.
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // Extrai o nome do usuario do token.
            String username = jwtTokenProvider.getUsername(token);

            // Carrega os detalhes do usuario.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Cria a autenticação com base no token e nos detalhes do usuario.
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            // Adiciona os detalhes da requisição a autenticação.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Define a autenticação no contexto de segurança.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continua a execução da cadeia de filtros.
        filterChain.doFilter(request, response);
    }

    // Metodo para extrair o token
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove o prefixo "Bearer ".
        }
        return null;
    }

}
