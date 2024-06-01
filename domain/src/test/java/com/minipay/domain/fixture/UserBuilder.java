package com.minipay.domain.fixture;

import com.minipay.domain.*;
import com.minipay.domain.valueobject.UserCredentials;
import com.minipay.domain.valueobject.UserDocument;
import com.minipay.domain.valueobject.Wallet;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Builder
@Accessors(fluent = true)
public class UserBuilder {
    private Integer id = 1;
    private String name = "seu antonio";
    private String type = "lojista";
    private UserCredentials credentials = new UserCredentials("antonio@fagundes.com", "123");
    private UserDocument document = new UserDocument("cnpj", "71755076000175");
    private BigDecimal balance = BigDecimal.ZERO;

    private UserBuilder() {}

    public UserBuilder withId(final Integer anId) {
        id = anId;
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

    public UserBuilder withBalance(final double aBalance) {
        balance = BigDecimal.valueOf(aBalance);
        return this;
    }

    public User build() {
        var wallet = new Wallet(balance);
        return type.equals("lojista") ?
                new ShopKeeper(id, name, credentials, document, wallet) :
                new CommonUser(id, name, credentials, document, wallet);
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }
}
