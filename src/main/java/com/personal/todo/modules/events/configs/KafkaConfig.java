package com.personal.todo.modules.events.configs;

import com.personal.todo.modules.task.events.TaskEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.personal.todo.modules.task.events.partitioners.TaskTimeBasedPartitioner;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("!test")
@ConditionalOnProperty(name = "app.message_broker.name", havingValue = "kafka")
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}") 
    private String bootstrapServers;

    @Value("${app.topics.task_events}")
    private String taskEventsTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic taskEventsTopic() {
        return new NewTopic(taskEventsTopic, 2, (short) 1);
    }

    @Bean
    public ProducerFactory<String, TaskEvent> taskProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, TaskTimeBasedPartitioner.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, TaskEvent> taskKafkaTemplate() {
        return new KafkaTemplate<>(taskProducerFactory());
    }
}
