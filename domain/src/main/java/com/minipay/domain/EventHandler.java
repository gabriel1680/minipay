package com.minipay.domain;

public interface EventHandler<T extends DomainEvent> {
    boolean canHandle(Class<?> event);
    void handle(T event);
}
