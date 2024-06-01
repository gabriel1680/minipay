package com.minipay.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class ShopKeeper extends User {
    public ShopKeeper(UUID id, String name, UserCredentials credentials, UserDocument document, Wallet wallet) {
        super(id, name, credentials, document, wallet);
    }

    public ShopKeeper(UUID id, String name, UserCredentials credentials, UserDocument document) {
        super(id, name, credentials, document);
    }

    @Override
    public String getType() {
        return "lojista";
    }

    @Override
    public void credit(BigDecimal aValue) {
        // Brakes Liskov Substitution
        throw new RuntimeException("A shopkeeper cannot credit");
    }

    @Override
    public void debit(BigDecimal value) {
        wallet = wallet.debit(value);
    }
}
