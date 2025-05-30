package com.personal.todo.modules.events.configs;

import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.task.events.TaskEventMessage;
import com.personal.todo.modules.task.events.adapters.KafkaTaskEventProducer;

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
    private KafkaTaskEventProducer kafkaTaskEventProducer;

    @Bean
    @Primary
    public EventPublisher<TaskEventMessage> taskEventPublisher() {
        return switch (brokerName.toLowerCase()) {
            case "kafka" -> kafkaTaskEventProducer;
            default -> throw new IllegalArgumentException("broker name not supported: " + brokerName);
        };
    }
}
