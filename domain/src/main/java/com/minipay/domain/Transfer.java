package com.minipay.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private UUID payerId;
    private UUID payeeId;
    private BigDecimal amount;

    public Transfer(final UUID id, final UUID payerId, final UUID payeeId, final BigDecimal amount) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
    }

    public static Transfer create(final String payerId, final String payeeId, final BigDecimal amount) {
        return new Transfer(
                UUID.randomUUID(),
                UUID.fromString(payerId),
                UUID.fromString(payeeId),
                amount
        );
    }

    public UUID getId() {
        return id;
    }

    public UUID getPayerId() {
        return payerId;
    }

    public UUID getPayeeId() {
        return payeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
