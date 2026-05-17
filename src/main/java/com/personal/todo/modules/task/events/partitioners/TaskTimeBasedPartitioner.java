package com.personal.todo.modules.task.events.partitioners;

import com.personal.todo.modules.task.events.TaskEvent;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

public class TaskTimeBasedPartitioner implements Partitioner {
    private final Logger logger = LoggerFactory.getLogger(TaskTimeBasedPartitioner.class);

    @Override
    public int partition(
            String topic,
            Object key,
            byte[] keyBytes,
            Object value,
            byte[] valueBytes,
            Cluster cluster
    ) {
        int partitionCount = cluster.partitionCountForTopic(topic);

        String errorMessage = "o topico devia conter 2 particoes mas " + partitionCount + " foram encontradas";

        if (partitionCount != 2) {
            logger.atError()
                    .setMessage(errorMessage)
                    .addKeyValue("topicName", topic)
                    .log();

            throw new RuntimeException(errorMessage);
        }

        if (value instanceof TaskEvent event) {
            LocalDateTime timestamp = event.getTimestamp();

            if (timestamp.getHour() < 12) {
                return 0;
            } else {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
