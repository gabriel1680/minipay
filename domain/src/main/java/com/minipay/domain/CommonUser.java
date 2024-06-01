package com.minipay.domain;

import com.minipay.domain.valueobject.UserCredentials;
import com.minipay.domain.valueobject.UserDocument;
import com.minipay.domain.valueobject.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public class CommonUser extends User {
    public CommonUser(UUID id, String name, UserCredentials credentials, UserDocument document, Wallet wallet) {
        super(id, name, credentials, document, wallet);
    }

    public CommonUser(UUID id, String name, UserCredentials credentials, UserDocument document) {
        super(id, name, credentials, document);
    }

    @Override
    public String getType() {
        return "usuário";
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
