package com.example.enaaskillsplatform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ENAA Skills API")
                        .version("1.0.0")
                        .description("Microservice de gestion des compétences et sous-compétences pour ENAA"));
    }
}
