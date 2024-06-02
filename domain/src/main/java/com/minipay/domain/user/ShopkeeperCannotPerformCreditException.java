package com.minipay.domain.user;

import com.minipay.domain.DomainException;

public class ShopkeeperCannotPerformCreditException extends DomainException {
    public ShopkeeperCannotPerformCreditException() {
        super("A Shopkeeper user cannot perform a credit operation");
    }
}
