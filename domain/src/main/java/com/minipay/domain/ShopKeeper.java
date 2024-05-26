package com.minipay.domain;

import java.math.BigDecimal;

public class ShopKeeper extends User {

    public ShopKeeper(Integer id, String name, UserCredentials credentials, String document) {
        super(id, name, credentials, document);
    }

    public ShopKeeper(Integer id, String name, UserCredentials credentials, String document, Wallet wallet) {
        super(id, name, credentials, document, wallet);
    }

    @Override
    public String getDocumentType() {
        return "cnpj";
    }

    @Override
    public void credit(BigDecimal aValue) {
        throw new RuntimeException("A shopkeeper cannot credit");
    }

    @Override
    public void debit(BigDecimal value) {
        wallet = wallet.debit(value);
    }
}
