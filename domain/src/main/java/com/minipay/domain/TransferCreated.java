package com.minipay.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferCreated(UUID id, UUID payerId, UUID payeeId, BigDecimal amount) implements DomainEvent {
    public static String NAME = "TransferCreated";

    public String name() {
        return NAME;
    }
}
