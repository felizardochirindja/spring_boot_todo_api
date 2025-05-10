package com.personal.todo.modules.events.configs;

import com.personal.todo.modules.events.entities.EventMessaging;
import com.personal.todo.modules.events.handlers.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class EventConfigMock {
    @Bean
    public EventPublisher eventPublisher() {
        return new EventPublisher() {
            @Override
            public void publish(String channel, String key, EventMessaging message) {}

            @Override
            public void publish(String channel, EventMessaging message) {}
        };
    }
}
