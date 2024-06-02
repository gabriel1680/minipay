package com.minipay.domain.user;

import com.minipay.domain.DomainException;

public class InsufficientFundsException extends DomainException {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}
