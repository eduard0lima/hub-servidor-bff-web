package br.jus.jfce.hubservidor.bffweb.shared.error;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
    Instant timestamp,
    String path,
    int status,
    ApiErrorCode errorCode,
    String message,
    List<String> details,
    String correlationId
) {
}
