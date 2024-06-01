package com.minipay.domain.event;

import java.util.*;

public class MemoryEventDispatcher implements DomainEventDispatcher, DomainEventRegister {
    private static MemoryEventDispatcher instance;

    private final List<EventHandler> handlers;

    private MemoryEventDispatcher() {
        handlers = new ArrayList<>();
    }

    public static MemoryEventDispatcher getInstance() {
        return instance != null ? instance : new MemoryEventDispatcher();
    }

    public static MemoryEventDispatcher fresh() {
        return new MemoryEventDispatcher();
    }

    public void register(final EventHandler handler) {
        handlers.add(handler);
    }

    public void publish(final DomainEvent event) {
        for (EventHandler handler : handlers) {
            if (handler.canHandle(event.getClass())) handler.handle(event);
        }
    }
}
