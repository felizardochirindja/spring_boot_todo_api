package com.personal.task.infra.configs;

import com.personal.task.business.app.actions.CreateUserAction;
import com.personal.task.business.app.ports.input.UserCreator;
import com.personal.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.task.infra.adapters.libs.dummyjson.DummyJsonTaskSyncFetcherByWebClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
