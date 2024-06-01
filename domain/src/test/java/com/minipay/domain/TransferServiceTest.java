package com.minipay.domain;

import com.minipay.domain.fixture.UserBuilder;
import com.minipay.domain.user.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransferServiceTest {

    @Test
    void performDebitCredit() {
        var payee = UserBuilder.aUser().ofType(UserType.SHOPKEEPER).withBalance(100.00).build();
        var payer = UserBuilder.aUser().ofType(UserType.COMMON).withBalance(100.00).build();
        var amount = BigDecimal.valueOf(50.00);
        final var transfer = TransferService.performDebitCredit(payer, payee, amount);
        Assertions.assertEquals("50.0", payer.getBalance().toString());
        Assertions.assertEquals("150.0", payee.getBalance().toString());
        Assertions.assertEquals(payer.getId(), transfer.getPayerId());
        Assertions.assertEquals(payee.getId(), transfer.getPayeeId());
        Assertions.assertEquals("50.0", transfer.getAmount().toString());
    }
}