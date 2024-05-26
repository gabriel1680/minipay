package com.minipay.domain;

import java.math.BigDecimal;

public class CommonUser extends User {
    public CommonUser(Integer id, String name, UserCredentials credentials, String document, Wallet wallet) {
        super(id, name, credentials, document, wallet);
    }

    public CommonUser(Integer id, String name, UserCredentials credentials, String document) {
        super(id, name, credentials, document);
    }

    @Override
    public String getDocumentType() {
        return "cpf";
    }

    @Override
    public void credit(BigDecimal aValue) {
        wallet = wallet.credit(aValue);
    }

    @Override
    public void debit(BigDecimal aValue) {
        wallet = wallet.debit(aValue);
    }
}
