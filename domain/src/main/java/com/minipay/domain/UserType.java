package com.minipay.domain;

import java.util.Arrays;
import java.util.Optional;

public enum UserType {
    SHOPKEEPER("lojista"),
    COMMON("usuário");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Optional<UserType> of(String aType) {
        return Arrays.stream(UserType.values())
                .filter((t) -> t.type.equalsIgnoreCase(aType))
                .findFirst();
    }
}
