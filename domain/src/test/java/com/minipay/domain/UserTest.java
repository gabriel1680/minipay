package com.minipay.domain;

import com.minipay.domain.valueobject.UserCredentials;
import com.minipay.domain.valueobject.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UserTest {

    class StubUser extends User {
        public StubUser(Integer id, String name, UserCredentials credentials, String document, Wallet wallet) {
            super(id, name, credentials, document, wallet);
        }

        @Override
        public String getDocumentType() {
            return "test";
        }

        @Override
        public void credit(BigDecimal aValue) {
        }

        @Override
        public void debit(BigDecimal aValue) {
        }
    }

    @Test
    void newUser() {
        Integer id = 1;
        String name = "John Doe";
        String document = "53935677073";
        String email = "john@doe.com";
        String password = "123";
        var wallet = new Wallet(BigDecimal.ZERO);
        var credentials = new UserCredentials(email, password);
        var sut = new StubUser(id, name, credentials, document, wallet);
        Assertions.assertEquals(sut.getId(), id);
        Assertions.assertEquals(sut.getName(), name);
        Assertions.assertEquals(sut.getDocumentType(), "test");
        Assertions.assertEquals(sut.getDocument(), document);
        Assertions.assertEquals(sut.getCredentials().email(), email);
        Assertions.assertEquals(sut.getCredentials().password(), password);
    }
}