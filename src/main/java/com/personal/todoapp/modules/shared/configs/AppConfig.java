package com.personal.todoapp.modules.shared.configs;

import com.personal.todoapp.modules.user.business.app.CreateUserAction;
import com.personal.todoapp.modules.user.business.app.ports.input.UserCreator;
import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.todoapp.modules.task.infra.adapters.libs.DummyJsonTaskSyncFetcherByWebClient;
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
