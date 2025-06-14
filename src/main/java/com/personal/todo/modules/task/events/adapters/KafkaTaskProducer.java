package com.personal.todo.modules.task.events.adapters;

import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.task.events.TaskEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.message_broker.name", havingValue = "kafka", matchIfMissing = true)
public class KafkaTaskProducer implements EventPublisher<TaskEvent> {
    @Autowired
    private KafkaTemplate<String, TaskEvent> taskKafkaTemplate;

    @Override
    public void publish(String topic, String key, TaskEvent event) {
        taskKafkaTemplate.send(topic, key, event);
    }

    @Override
    public void publish(String topic, TaskEvent event) {
        taskKafkaTemplate.send(topic, event);
    }
}
