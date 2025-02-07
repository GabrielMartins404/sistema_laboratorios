package com.sistema_laboratorios.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Tela com o propósito de configurar o CORS para permitir que outras aplicações usem a API
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
}
