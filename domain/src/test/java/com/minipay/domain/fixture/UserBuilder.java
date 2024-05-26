package com.minipay.domain.fixture;

import com.minipay.domain.*;

import java.math.BigDecimal;

public class UserBuilder {
    private Integer id = 1;
    private String name = "seu antonio";
    private UserType type = UserType.SHOPKEEPER;
    private UserCredentials credentials = new UserCredentials("antonio@fagundes.com", "123");
    private String document = "71755076000175";
    private BigDecimal balance = BigDecimal.ZERO;

    private UserBuilder() {}

    public UserBuilder withId(final Integer anId) {
        id = anId;
        return this;
    }

    public UserBuilder ofType(final UserType aType) {
        type = aType;
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

    public UserBuilder withDocument(final String aDocument) {
        document = aDocument;
        return this;
    }

    public UserBuilder withBalance(final double aBalance) {
        balance = BigDecimal.valueOf(aBalance);
        return this;
    }

    public User build() {
        var wallet = new Wallet(balance);
        return type.equals(UserType.SHOPKEEPER) ?
                new ShopKeeper(id, name, credentials, document, wallet) :
                new CommonUser(id, name, credentials, document, wallet);
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }
}
