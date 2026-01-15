package br.jus.jfce.hubservidor.bffweb.config;

import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilderCustomizer;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder
            .timeZone(ZoneId.of("UTC").toString())
            .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
}
