package br.jus.jfce.hubservidor.bffweb.shared.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
