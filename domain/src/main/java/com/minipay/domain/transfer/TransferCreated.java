package com.minipay.domain.transfer;

import com.minipay.domain.event.DomainEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransferCreated(UUID id, UUID payerId, UUID payeeId, BigDecimal amount) implements DomainEvent {
    public static String NAME = "TransferCreated";
    public static Instant RAISED_AT = Instant.now();

    public static TransferCreated of(final Transfer transfer) {
        return new TransferCreated(
          transfer.getId(),
          transfer.getPayerId(),
          transfer.getPayeeId(),
          transfer.getAmount()
        );
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Instant raisedAt() {
        return RAISED_AT;
    }
}
