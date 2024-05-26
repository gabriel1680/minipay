package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CommonUserTest {

    final String FIFTY = "50.00";
    final BigDecimal FIFTY_BIG_DECIMAL = new BigDecimal(FIFTY);

    CommonUser sut;

    @BeforeEach
    void setup() {
        sut = (CommonUser) UserBuilder.aUser().ofType(UserType.COMMON).withBalance(100.0).build();
    }

    @Test
    void credit() {
        sut.credit(FIFTY_BIG_DECIMAL);
        Assertions.assertEquals(FIFTY, sut.wallet.balance().toString());
    }

    @Test
    void debit() {
        sut.debit(FIFTY_BIG_DECIMAL);
        Assertions.assertEquals("150.00", sut.wallet.balance().toString());
    }
}