package com.minipay.domain.user;

import com.minipay.domain.HashFacade;

public record UserCredentials(String email, String password) {
    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = HashFacade.start().hash(password);
    }
}
