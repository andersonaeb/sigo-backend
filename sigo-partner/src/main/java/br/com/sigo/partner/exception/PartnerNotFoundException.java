package br.com.sigo.partner.exception;

public class PartnerNotFoundException extends RuntimeException {

    public PartnerNotFoundException(String message) {
        super(message);
    }
}
