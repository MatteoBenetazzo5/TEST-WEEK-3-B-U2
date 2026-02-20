package matteobenetazzo.testweek3bu2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTCheckerFilter jwtCheckerFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtCheckerFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/utenti").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}

