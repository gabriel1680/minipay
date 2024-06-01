package com.minipay.domain.event;

import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent extends Serializable {
    Instant raisedAt();
    String name();
}
