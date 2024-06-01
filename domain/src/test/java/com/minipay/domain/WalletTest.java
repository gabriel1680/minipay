package com.minipay.domain;

import com.minipay.domain.valueobject.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class WalletTest {

    final BigDecimal ZERO = new BigDecimal("0.00");
    final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");

    Wallet sut;

    @BeforeEach
    void setUp() {
        sut = new Wallet(ZERO);
    }

    @Test
    void debit() {
        sut = sut.debit(ONE_HUNDRED);
        Assertions.assertEquals(ONE_HUNDRED, sut.balance());
    }

    @Test
    void credit() {
        sut = new Wallet(ONE_HUNDRED);
        sut = sut.credit(ONE_HUNDRED);
        Assertions.assertEquals(ZERO, sut.balance());
    }

    @Test
    void creditWithoutMoney() {
        Assertions.assertThrows(RuntimeException.class, () -> sut.credit(ONE_HUNDRED));
    }
}
