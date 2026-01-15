package br.jus.jfce.hubservidor.bffweb.shared.error;

import org.springframework.http.HttpStatus;

public class IntegrationException extends RuntimeException {

    private final HttpStatus status;

    public IntegrationException(String message) {
        this(message, HttpStatus.BAD_GATEWAY);
    }

    public IntegrationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
