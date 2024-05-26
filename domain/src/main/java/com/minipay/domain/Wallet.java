package com.minipay.domain;

import java.math.BigDecimal;

public record Wallet(BigDecimal balance) {

    public Wallet credit(BigDecimal value) {
        if (balance.compareTo(value) < 0) throw new RuntimeException();
        return new Wallet(balance.subtract(value));
    }

    public Wallet debit(BigDecimal value) {
        return new Wallet(balance.add(value));
    }
}
