package com.example.Casino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключение CSRF для упрощения тестирования через Postman
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/register", "/login").permitAll() // Разрешить доступ к /register и /login без авторизации
                                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/home", true) // Успешный редирект на /home
                                .permitAll() // Разрешить доступ ко всем элементам формы логина
                )
                .logout(logout ->
                        logout.permitAll() // Разрешить всем доступ к logout
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}