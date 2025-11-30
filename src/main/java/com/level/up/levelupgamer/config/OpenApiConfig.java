package com.level.up.levelupgamer.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Level Up Gamer API")
                        .version("1.0")
                        .description("Documentación automática de la API para el sistema Level-Up Gamer")
                        .contact(new Contact()
                                .name("Leonardo Amundarain")
                                .email("soporte@levelupgamer.cl")
                        )
                );
    }
}
