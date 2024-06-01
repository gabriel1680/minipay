package com.minipay.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_value")
    private String documentValue;

    public UserJpaEntity(UUID id, String name, String type, String email, String password, String documentType, String documentValue) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
        this.documentType = documentType;
        this.documentValue = documentValue;
    }

    public UserJpaEntity() {}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
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

    public void setType(String type) {
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
