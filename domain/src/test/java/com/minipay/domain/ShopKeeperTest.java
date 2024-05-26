package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ShopKeeperTest {

    final BigDecimal FIFTY_BIG_DECIMAL = new BigDecimal("50.00");

    ShopKeeper sut;

    @BeforeEach
    void setUp() {
        sut = (ShopKeeper) UserBuilder.aUser().withBalance(100.0).build();
    }

    @Test
    void credit() {
        Assertions.assertThrows(RuntimeException.class, () -> sut.credit(FIFTY_BIG_DECIMAL));
    }

    @Test
    void debit() {
        sut.debit(FIFTY_BIG_DECIMAL);
        Assertions.assertEquals("150.00", sut.wallet.balance().toString());
    }
}