package com.minipay.domain.user;

import com.minipay.domain.Entity;

import java.math.BigDecimal;
import java.util.UUID;

public class User extends Entity {
    private String name;
    private UserType type;
    private UserCredentials credentials;
    private UserDocument document;
    private BigDecimal balance;

    public User(UUID id, String name, UserType type, UserCredentials credentials, UserDocument document, BigDecimal balance) {
        super(id);
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
                UserType.of(type),
                new UserCredentials(email, password),
                new UserDocument(documentType, documentValue),
                new BigDecimal("0.00")
        );
    }

    public void credit(BigDecimal aValue) {
        if (balance.compareTo(aValue) < 0) throw new InsufficientFundsException();
        if (type.equals(UserType.SHOPKEEPER))
            throw new ShopkeeperCannotPerformCreditException();
        balance = balance.subtract(aValue);
    }

    public void debit(BigDecimal aValue) {
        balance = balance.add(aValue);
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
