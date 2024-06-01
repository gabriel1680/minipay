package com.minipay.domain.event;

@FunctionalInterface
public interface DomainEventRegister {
    void register(EventHandler<DomainEvent>  handler);
}
