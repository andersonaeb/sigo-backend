package br.com.sigo.partner.exception;

public class RequirementNotFoundException extends RuntimeException {

    public RequirementNotFoundException(String message) {
        super(message);
    }
}
