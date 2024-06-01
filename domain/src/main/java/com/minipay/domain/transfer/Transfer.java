package com.minipay.domain.transfer;

import com.minipay.domain.Entity;
import com.minipay.domain.event.DomainEvent;

import java.math.BigDecimal;
import java.util.*;

public class Transfer extends Entity {
    private UUID payerId;
    private UUID payeeId;
    private BigDecimal amount;


    public Transfer(final UUID id, final UUID payerId, final UUID payeeId, final BigDecimal amount) {
        super(id);
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
    }

    public Transfer(final UUID id, final UUID payerId, final UUID payeeId, final BigDecimal amount, final List<DomainEvent> events) {
        super(id, events);
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
    }

    public static Transfer create(final String payerId, final String payeeId, final BigDecimal amount) {
        final var transfer = new Transfer(
                UUID.randomUUID(),
                UUID.fromString(payerId),
                UUID.fromString(payeeId),
                amount
        );
        transfer.registerEvent(TransferCreated.of(transfer));
        return transfer;
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
