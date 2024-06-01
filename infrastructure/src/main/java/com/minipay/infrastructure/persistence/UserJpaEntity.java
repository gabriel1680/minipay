package com.minipay.infrastructure.persistence;

import com.minipay.domain.UserType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private UserType type;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_value")
    private String documentValue;

    @Column(name = "balance")
    private BigDecimal balance;

    public UserJpaEntity(UUID id, String name, UserType type, String email, String password, String documentType, String documentValue, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
        this.documentType = documentType;
        this.documentValue = documentValue;
        this.balance = balance;
    }

    public UserJpaEntity() {}

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentValue() {
        return documentValue;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setDocumentValue(String documentValue) {
        this.documentValue = documentValue;
    }
}
