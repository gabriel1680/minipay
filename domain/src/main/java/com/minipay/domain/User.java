package com.minipay.domain;

import java.math.BigDecimal;

public abstract class User {
    private Integer id;
    private String name;
    private UserCredentials credentials;
    private String document;
    protected Wallet wallet;

    public User(Integer id, String name, UserCredentials credentials, String document, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.credentials = credentials;
        this.document = document;
        this.wallet = wallet;
    }

    public User(Integer id, String name, UserCredentials credentials, String document) {
        this.id = id;
        this.name = name;
        this.credentials = credentials;
        this.document = document;
        this.wallet = new Wallet(new BigDecimal("0.00"));
    }

    public abstract String getDocumentType();

    public abstract void credit(BigDecimal aValue);

    public abstract void debit(BigDecimal aValue);

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }
}
