package com.minipay.domain.fixture;

import com.minipay.domain.*;
import com.minipay.domain.valueobject.UserCredentials;
import com.minipay.domain.valueobject.UserDocument;

import java.math.BigDecimal;
import java.util.UUID;

public class UserBuilder {
    private UUID id = UUID.fromString("de9991ba-7dbf-43dc-83e9-0c96bcacb9b0");
    private String name = "seu antonio";
    private UserType type = UserType.COMMON;
    private UserCredentials credentials = new UserCredentials("antonio@fagundes.com", "123");
    private UserDocument document = new UserDocument("cnpj", "71755076000175");
    private BigDecimal balance = BigDecimal.ZERO;

    private UserBuilder() {}

    public UserBuilder withId(final String anId) {
        id = UUID.fromString(anId);
        return this;
    }

    public UserBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public UserBuilder withCredentials(final String anEmail, final String aPassword) {
        credentials = new UserCredentials(anEmail, aPassword);
        return this;
    }

    public UserBuilder withDocument(final String documentType, final String aDocument) {
        document = new UserDocument(documentType, aDocument);
        return this;
    }

    public UserBuilder ofType(UserType aType) {
        type = aType;
        return this;
    }

    public UserBuilder withBalance(final double aBalance) {
        balance = BigDecimal.valueOf(aBalance);
        return this;
    }

    public User build() {
        return new User(
                id,
                name,
                type,
                credentials,
                document,
                balance
        );
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }
}
