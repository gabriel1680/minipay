package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import com.minipay.domain.user.User;
import com.minipay.domain.user.UserType;
import com.minipay.domain.user.UserCredentials;
import com.minipay.domain.user.UserDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class UserTest {

    @Test
    void constructor() {
        final var id = UUID.randomUUID();
        final var name = "John Doe";
        final var type = UserType.COMMON;
        final var documentType = "cpf";
        final var documentValue = "53935677073";
        final var email = "john@doe.com";
        final var password = "123";
        final var balance = BigDecimal.ZERO;
        var sut = new User(
                id,
                name,
                type,
                new UserCredentials(email, password),
                new UserDocument(documentType, documentValue),
                balance
        );
        Assertions.assertEquals(sut.getId(), id);
        Assertions.assertEquals(sut.getName(), name);
        Assertions.assertEquals(sut.getType(), type);
        Assertions.assertEquals(sut.getDocument().type(), documentType);
        Assertions.assertEquals(sut.getDocument().value(), documentValue);
        Assertions.assertEquals(sut.getCredentials().email(), email);
        Assertions.assertEquals(sut.getCredentials().password(), password);
    }

    @Test
    void factory() {
        final var name = "John Doe";
        final var type = UserType.COMMON;
        final var documentType = "cpf";
        final var documentValue = "53935677073";
        final var email = "john@doe.com";
        final var password = "123";
        var sut = User.create(
                name,
                type.toString(),
                email,
                password,
                documentType,
                documentValue
        );
        Assertions.assertInstanceOf(UUID.class, sut.getId());
        Assertions.assertEquals(sut.getName(), name);
        Assertions.assertEquals(sut.getType(), type);
        Assertions.assertEquals(sut.getDocument().type(), documentType);
        Assertions.assertEquals(sut.getDocument().value(), documentValue);
        Assertions.assertEquals(sut.getCredentials().email(), email);
        Assertions.assertEquals(sut.getCredentials().password(), password);
        Assertions.assertEquals(0, sut.getBalance().compareTo(BigDecimal.ZERO));
    }

    @Test
    void debit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        user.debit(new BigDecimal("10.0"));
        Assertions.assertEquals("30.0", user.getBalance().toString());
    }

    @Test
    void credit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        user.credit(new BigDecimal("10.0"));
        Assertions.assertEquals("10.0", user.getBalance().toString());
    }

    @Test
    void shopkeeperCredit() {
        final var user = UserBuilder.aUser().ofType(UserType.SHOPKEEPER).withBalance(20d).build();
        Assertions.assertThrows(RuntimeException.class, () -> user.credit(new BigDecimal("10.0")));
        Assertions.assertEquals("20.0", user.getBalance().toString());
    }

    @Test
    void noBalanceCredit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        Assertions.assertThrows(RuntimeException.class, () -> user.credit(new BigDecimal("30.0")));
        Assertions.assertEquals("20.0", user.getBalance().toString());
    }
}