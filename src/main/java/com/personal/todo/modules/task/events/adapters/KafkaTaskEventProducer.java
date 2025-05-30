package com.personal.todo.modules.task.events.adapters;

import com.personal.todo.modules.events.entities.EventMessaging;
import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.task.events.TaskEventMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.message_broker.name", havingValue = "kafka", matchIfMissing = true)
public class KafkaTaskEventProducer implements EventPublisher<TaskEventMessage> {
    @Autowired
    private KafkaTemplate<String, EventMessaging> kafkaTemplate;

    @Override
    public void publish(String topic, String key, TaskEventMessage event) {
        kafkaTemplate.send(topic, key, event);
    }

    @Override
    public void publish(String topic, TaskEventMessage event) {
        kafkaTemplate.send(topic, event);
    }
}
