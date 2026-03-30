package com.giftapp.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // 🔥 disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/test").permitAll()  // allow test
                        .anyRequest().permitAll()              // allow everything for now
                )
                .httpBasic(Customizer.withDefaults())      // basic auth (optional)
                .formLogin(form -> form.disable());        // disable login page

        return http.build();
    }
}