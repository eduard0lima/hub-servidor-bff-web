package br.jus.jfce.hubservidor.api.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.clients.core")
public record CoreClientProperties(String baseUrl) {
}
