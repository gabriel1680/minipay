package com.minipay.domain;

import java.math.BigDecimal;

public class TransferService {
    public static Transfer performDebitCredit(final User payer, final User payee, final BigDecimal amount) {
        payer.credit(amount);
        payee.debit(amount);
        return Transfer.create(payer.getId().toString(), payee.getId().toString(), amount);
    }
}
