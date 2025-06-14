package com.personal.todo.modules.events.configs;

import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.task.events.TaskEvent;
import com.personal.todo.modules.task.events.adapters.KafkaTaskProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EventConfig {
    @Value("${app.message_broker.name:kafka}")
    private String brokerName;

    @Autowired(required = false)
    private KafkaTaskProducer kafkaTaskProducer;

    @Bean
    @Primary
    public EventPublisher<TaskEvent> taskEventPublisher() {
        return switch (brokerName.toLowerCase()) {
            case "kafka" -> kafkaTaskProducer;
            default -> throw new IllegalArgumentException("broker name not supported: " + brokerName);
        };
    }
}
