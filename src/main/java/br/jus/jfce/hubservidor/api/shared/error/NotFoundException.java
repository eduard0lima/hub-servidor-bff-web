package br.jus.jfce.hubservidor.api.shared.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
