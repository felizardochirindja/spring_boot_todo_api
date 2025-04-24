package com.personal.todoapp.modules.events.partitioners;

import com.personal.todoapp.modules.task.events.TaskEventMessage;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.time.LocalDateTime;
import java.util.Map;

public class TimeBasedPartitioner implements Partitioner {
    @Override
    public int partition(
            String topic,
            Object key,
            byte[] keyBytes,
            Object value,
            byte[] valueBytes,
            Cluster cluster
    ) {
        if (cluster.partitionCountForTopic(topic) < 2) {
            return 0;
        }

        if (value instanceof TaskEventMessage event) {
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
