package com.personal.todo.modules.events.handlers;

import com.personal.todo.modules.events.entities.EventMessaging;

public interface EventPublisher<T extends EventMessaging> {
    void publish(String channel, String key, T event);
    void publish(String channel, T event);
}
