package br.jus.jfce.hubservidor.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Hub Servidor BFF Web")
                .description("BFF REST do Hub do Servidor (JFCE) para o hub-servidor-web.")
                .version("v1"));
    }
}
