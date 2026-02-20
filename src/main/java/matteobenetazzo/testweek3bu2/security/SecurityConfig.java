package matteobenetazzo.testweek3bu2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTCheckerFilter jwtCheckerFilter) throws Exception {
        http
                // disabilito CSRF perché uso API REST
                .csrf(csrf -> csrf.disable())

                // inserisco il filtro deciso da me
                .addFilterBefore(jwtCheckerFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(auth -> auth
                        // lascio liberi register e login
                        .requestMatchers("/auth/**").permitAll()
                        // tutto il resto lo proteggo: serve autenticazione
                        .anyRequest().authenticated()
                );

        // restituisco la configurazione finale
        return http.build();
    }
}

