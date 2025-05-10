package com.personal.todo.modules.events.handlers;

import com.personal.todo.modules.events.entities.EventMessaging;

public interface EventPublisher {
    void publish(String channel, String key, EventMessaging message);
    void publish(String channel, EventMessaging message);
}
