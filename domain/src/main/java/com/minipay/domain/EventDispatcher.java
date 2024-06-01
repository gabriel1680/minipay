package com.minipay.domain;

import java.util.*;

public class EventDispatcher {
    private static EventDispatcher instance;

    private final List<EventHandler> handlers;

    private EventDispatcher() {
        handlers = new ArrayList<>();
    }

    public static EventDispatcher getInstance() {
        return instance != null ? instance : new EventDispatcher();
    }

    public static EventDispatcher fresh() {
        return new EventDispatcher();
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
