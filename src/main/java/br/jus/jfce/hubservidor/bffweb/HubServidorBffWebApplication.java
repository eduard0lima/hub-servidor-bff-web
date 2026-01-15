package br.jus.jfce.hubservidor.bffweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HubServidorBffWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubServidorBffWebApplication.class, args);
    }
}
