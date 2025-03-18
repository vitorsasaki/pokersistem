package com.pokersistem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permite todas as origens durante o desenvolvimento
        config.addAllowedOrigin("*");

        // Permite todos os headers
        config.addAllowedHeader("*");

        // Permite todos os métodos HTTP
        config.addAllowedMethod("*");

        // Permite credenciais
        config.setAllowCredentials(true);

        // Tempo máximo que o navegador deve armazenar em cache a resposta do preflight
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}