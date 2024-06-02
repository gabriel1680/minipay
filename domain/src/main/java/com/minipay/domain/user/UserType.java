package com.minipay.domain.user;

import java.util.Arrays;

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

    public static UserType of(String aType) {
        return Arrays.stream(UserType.values())
                .filter((t) -> t.getType().equalsIgnoreCase(aType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid user type"));
    }
}
