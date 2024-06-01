package com.minipay.domain;

import java.util.UUID;

public class UserFactory {
    public static User create(UUID id, String name, String email, String password, String documentType, String documentValue) {
        final var credentials = new UserCredentials(email, password);
        final var document = new UserDocument(documentType, documentValue);
        return documentType.equals("cnpj") ?
                new ShopKeeper(id, name, credentials, document) :
                new CommonUser(id, name, credentials, document);
    }
}
