package com.personal.todo.modules.events.configs;

import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.events.handlers.adapters.KafkaEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class EventConfig {
    @Value("${app.message_broker.name:kafka}")
    private String brokerName;

    @Autowired(required = false)
    private KafkaEventPublisher kafkaPublisher;

    @Bean
    @Primary
    public EventPublisher eventPublisher() {
        return switch (brokerName.toLowerCase()) {
            case "kafka" -> kafkaPublisher;
            default -> throw new IllegalArgumentException("broker name not supported: " + brokerName);
        };
    }
}
