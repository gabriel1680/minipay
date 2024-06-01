package com.minipay.domain;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class User {
    private UUID id;
    private String name;
    private UserCredentials credentials;
    private UserDocument document;
    protected Wallet wallet;

    public User(UUID id, String name, UserCredentials credentials, UserDocument document, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.credentials = credentials;
        this.document = document;
        this.wallet = wallet;
    }

    public User(UUID id, String name, UserCredentials credentials, UserDocument document) {
        this.id = id;
        this.name = name;
        this.credentials = credentials;
        this.document = document;
        this.wallet = new Wallet(new BigDecimal("0.00"));
    }

    public abstract String getType();

    public abstract void credit(BigDecimal aValue);

    public abstract void debit(BigDecimal aValue);

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserDocument getDocument() {
        return document;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }
}
