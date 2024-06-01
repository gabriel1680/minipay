package com.minipay.domain.event;

@FunctionalInterface
public interface DomainEventDispatcher {
    void publish(DomainEvent event);
}
