package com.personal.todo.modules.events.handlers.adapters;

import com.personal.todo.modules.events.entities.EventMessaging;
import com.personal.todo.modules.events.handlers.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.message_broker.name", havingValue = "kafka", matchIfMissing = true)
public class KafkaEventPublisher implements EventPublisher {
    @Autowired
    private KafkaTemplate<String, EventMessaging> kafkaTemplate;

    @Override
    public void publish(String topic, String key, EventMessaging message) {
        kafkaTemplate.send(topic, key, message);
    }

    @Override
    public void publish(String topic, EventMessaging message) {
        kafkaTemplate.send(topic, message);
    }
}
