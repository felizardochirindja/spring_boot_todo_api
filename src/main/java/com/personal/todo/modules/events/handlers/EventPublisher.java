package com.personal.todo.modules.events.handlers;

import com.personal.todo.modules.events.entities.DomainEvent;

public interface EventPublisher<T extends DomainEvent> {
    void publish(String channel, String key, T event);
    void publish(String channel, T event);
}
