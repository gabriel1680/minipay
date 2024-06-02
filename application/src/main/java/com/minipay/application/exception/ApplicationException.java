package com.minipay.application.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable cause) {
        super(message, cause, true, false);
    }

    public ApplicationException(String message) {
        this(message, null);
    }
}
