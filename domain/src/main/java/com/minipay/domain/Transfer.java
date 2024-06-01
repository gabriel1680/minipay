package com.minipay.domain;

import java.math.BigDecimal;
import java.util.*;

public class Transfer {
    private UUID id;
    private UUID payerId;
    private UUID payeeId;
    private BigDecimal amount;
    private final List<DomainEvent> events;

    public Transfer(final UUID id, final UUID payerId, final UUID payeeId, final BigDecimal amount, final List<DomainEvent> events) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.events = events;
    }

    public Transfer(final UUID id, final UUID payerId, final UUID payeeId, final BigDecimal amount) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        events = new ArrayList<>();
    }

    public static Transfer create(final String payerId, final String payeeId, final BigDecimal amount) {
        final UUID id = UUID.randomUUID();
        final UUID payerUUID = UUID.fromString(payerId);
        final UUID payeeUUID = UUID.fromString(payeeId);
        final List<DomainEvent> events = List.of(new TransferCreated(id, payerUUID, payeeUUID, amount));
        return new Transfer(
                id,
                payerUUID,
                payeeUUID,
                amount,
                events
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

    public List<DomainEvent> getEvents() {
        return new ArrayList<>(events);
    }
}
