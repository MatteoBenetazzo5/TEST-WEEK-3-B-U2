package matteobenetazzo.testweek3bu2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // recupero l'header Authorization dalla richiesta
        String authHeader = request.getHeader("Authorization");

        // se non trovo il token oppure non inizia con "Bearer " lascio proseguire la richiesta
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // estraggo il token rimuovendo "Bearer "
        String token = authHeader.substring(7);

        try {
            // verifico il token e recupero il: id utente
            String subject = jwtTools.verifyTokenAndGetSubject(token);

            // creo l'oggetto di autenticazione e lo inserisco nel SecurityContext
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(subject, null, null);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception ex) {
            // se il token non è valido restituisco 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
