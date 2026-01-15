package br.jus.jfce.hubservidor.bffweb.domain;

import java.time.Instant;

public record PingStatus(String status, Instant timestamp) {
}
