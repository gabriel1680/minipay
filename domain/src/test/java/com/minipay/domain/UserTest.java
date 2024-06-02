package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import com.minipay.domain.user.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(sut.getId(), id);
        assertEquals(sut.getName(), name);
        assertEquals(sut.getType(), type);
        assertEquals(sut.getDocument().type(), documentType);
        assertEquals(sut.getDocument().value(), documentValue);
        assertEquals(sut.getCredentials().email(), email);
        // hashed
        assertNotEquals(sut.getCredentials().password(), password);
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
                type.getType(),
                email,
                password,
                documentType,
                documentValue
        );
        assertInstanceOf(UUID.class, sut.getId());
        assertEquals(sut.getName(), name);
        assertEquals(sut.getType(), type);
        assertEquals(sut.getDocument().type(), documentType);
        assertEquals(sut.getDocument().value(), documentValue);
        assertEquals(sut.getCredentials().email(), email);
        assertEquals(0, sut.getBalance().compareTo(BigDecimal.ZERO));
        // hashed
        assertNotEquals(sut.getCredentials().password(), password);
    }

    @Test
    void debit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        user.debit(new BigDecimal("10.0"));
        assertEquals("30.0", user.getBalance().toString());
    }

    @Test
    void credit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        user.credit(new BigDecimal("10.0"));
        assertEquals("10.0", user.getBalance().toString());
    }

    @Test
    void shopkeeperCredit() {
        final var user = UserBuilder.aUser().ofType(UserType.SHOPKEEPER).withBalance(20d).build();
        assertThrows(
                ShopkeeperCannotPerformCreditException.class, () -> user.credit(new BigDecimal("10.0"))
        );
        assertEquals("20.0", user.getBalance().toString());
    }

    @Test
    void noBalanceCredit() {
        final var user = UserBuilder.aUser().withBalance(20d).build();
        assertThrows(InsufficientFundsException.class, () -> user.credit(new BigDecimal("30.0")));
        assertEquals("20.0", user.getBalance().toString());
    }
}