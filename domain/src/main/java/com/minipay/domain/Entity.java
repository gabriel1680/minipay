package com.minipay.domain;

import com.minipay.domain.event.DomainEvent;
import com.minipay.domain.event.DomainEventDispatcher;

import java.util.*;

public abstract class Entity {
    protected final UUID id;
    private final List<DomainEvent> events;

    public Entity(final UUID id) {
        this(id, null);
    }

    public Entity(final UUID id, final List<DomainEvent> events) {
        this.id = Objects.requireNonNull(id);
        this.events = new ArrayList<>(events == null ? Collections.emptyList() : events);
    }

    public UUID getId() {
        return id;
    }

    public List<DomainEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void publishEvents(final DomainEventDispatcher dispatcher) {
        if (dispatcher == null) return;
        getEvents().forEach(dispatcher::publish);
        events.clear();
    }

    public void registerEvent(final DomainEvent event) {
        if (event == null) return;
        events.add(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
