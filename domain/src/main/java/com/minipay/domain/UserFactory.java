package com.minipay.domain;

public class UserFactory {
    public static User create(Integer id, String name, String email, String password, String documentType, String documentValue) {
        final var credentials = new UserCredentials(email, password);
        return documentType.equals("cnpj") ?
                new ShopKeeper(id, name, credentials, documentValue) :
                new CommonUser(id, name, credentials, documentValue);
    }
}
