package com.minipay.domain;

import com.minipay.domain.valueobject.UserCredentials;
import com.minipay.domain.valueobject.UserDocument;

import java.math.BigDecimal;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private UserType type;
    private UserCredentials credentials;
    private UserDocument document;
    private BigDecimal balance;

    public User(UUID id, String name, UserType type, UserCredentials credentials, UserDocument document, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.credentials = credentials;
        this.document = document;
        this.balance = balance;
    }

    public static User create(String name, String type, String email, String password, String documentType, String documentValue) {
        return new User(
                UUID.randomUUID(),
                name,
                UserType.of(type).orElseThrow(() -> new RuntimeException("Invalid user type")),
                new UserCredentials(email, password),
                new UserDocument(documentType, documentValue),
                new BigDecimal("0.00")
        );
    }

    public void credit(BigDecimal aValue) {
        if (balance.compareTo(aValue) < 0)
            throw new RuntimeException("Invalid amount");
        if (type.equals(UserType.SHOPKEEPER))
            throw new RuntimeException("A Shopkeeper is not allowed to perform a credit operation");
        balance = balance.subtract(aValue);
    }

    public void debit(BigDecimal aValue) {
        balance = balance.add(aValue);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public UserDocument getDocument() {
        return document;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }
}
