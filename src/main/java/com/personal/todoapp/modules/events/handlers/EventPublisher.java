package com.personal.todoapp.modules.events.handlers;

import com.personal.todoapp.modules.events.entities.EventMessaging;

public interface EventPublisher {
    void publish(String channel, String key, EventMessaging message);
    void publish(String channel, EventMessaging message);
}
