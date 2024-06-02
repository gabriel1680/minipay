package com.minipay.application.exception;

public class EmailAlreadyTakenException extends ApplicationException {
    public EmailAlreadyTakenException(final String email) {
        super("The email '%s' is already taken".formatted(email));
    }
}
