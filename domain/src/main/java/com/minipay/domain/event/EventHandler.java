package com.minipay.domain.event;

public interface EventHandler<T extends DomainEvent> {
    boolean canHandle(Class<?> event);
    void handle(T event);
}
