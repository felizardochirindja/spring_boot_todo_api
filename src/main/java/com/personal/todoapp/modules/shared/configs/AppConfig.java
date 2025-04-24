package com.personal.todoapp.modules.shared.configs;

import com.personal.todoapp.modules.user.business.app.CreateUserAction;
import com.personal.todoapp.modules.user.business.app.ports.input.UserCreator;
import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.todoapp.modules.task.infra.adapters.libs.DummyJsonTaskSyncFetcherByWebClient;
import com.personal.todoapp.modules.events.handlers.EventPublisher;
import com.personal.todoapp.modules.events.handlers.adapters.KafkaEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class AppConfig {
    @Bean
    public RemoteTaskSyncFetcher remoteTaskSyncFetcher() {
        return new DummyJsonTaskSyncFetcherByWebClient();
    }

    @Bean
    public UserCreator userCreator() {
        return new CreateUserAction();
    }

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
