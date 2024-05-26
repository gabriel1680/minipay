package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransferServiceTest {

    @Test
    void performDebitCredit() {
        var payee = UserBuilder.aUser().ofType(UserType.COMMON).withBalance(100.00).build();
        var payer = UserBuilder.aUser().ofType(UserType.COMMON).withBalance(100.00).build();
        var amount = BigDecimal.valueOf(50.00);
        TransferService.performDebitCredit(payer, payee, amount);
        Assertions.assertEquals("50.0", payer.wallet.balance().toString());
        Assertions.assertEquals("150.0", payee.wallet.balance().toString());
    }
}