package br.jus.jfce.hubservidor.api.domain;

import java.time.Instant;

public record PingStatus(String status, Instant timestamp) {
}
