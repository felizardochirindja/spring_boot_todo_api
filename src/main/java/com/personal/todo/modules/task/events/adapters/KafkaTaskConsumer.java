package com.personal.todo.modules.task.events.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.todo.modules.events.handlers.EventConsumer;
import com.personal.todo.modules.task.business.app.actions.TaskActions;
import com.personal.todo.modules.task.events.TaskEvent;
import com.personal.todo.modules.task.events.TaskEventName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@ConditionalOnProperty(name = "app.message_broker.name", havingValue = "kafka", matchIfMissing = true)
public class KafkaTaskConsumer implements EventConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskActions taskActions;
    private static final Logger logger = LoggerFactory.getLogger(KafkaTaskConsumer.class);

    @KafkaListener(topics = "task_events", groupId = "task_consumer_group")
    @Override
    public void consume(String event) {
        try {
            TaskEvent taskEvent = objectMapper.readValue(event, TaskEvent.class);

            logger.atInfo()
                    .setMessage("Received task event!")
                    .addKeyValue("taskId", taskEvent.getTaskId())
                    .addKeyValue("taskName", taskEvent.getName())
                    .log();

            processEvent(taskEvent);
        } catch (Exception e) {
            logger.error("Error processing task event: {}", e);
        }
    }

    private void processEvent(TaskEvent event) {
        TaskEventName eventName = TaskEventName.valueOf(event.getName());

        switch (eventName) {
            case TaskEventName.TASK_CREATED:
                taskActions.persistLog(eventName, event.getTaskId(), event.getTitle());
                break;
            case TaskEventName.TASK_UPDATED:
                break;
            default:
                logger.warn("Unknown event type: {}", event.getName());
        }
    }
}
