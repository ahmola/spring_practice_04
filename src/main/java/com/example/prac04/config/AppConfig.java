package com.example.prac04.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class AppConfig {

    @Bean
    public SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http)throws Exception{

        http
                .cors().disable()
                .csrf().disable()
                .authorizeExchange(auth -> {
                    auth.anyExchange().permitAll();
                });

        return http.build();
    }
}
