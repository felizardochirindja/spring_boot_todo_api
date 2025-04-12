package com.personal.taskie.infra.configs;

import com.personal.taskie.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.taskie.infra.adapters.libs.dummyjson.DummyJsonTaskSyncFetcherByWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RemoteTaskSyncFetcher remoteTaskSyncFetcher() {
        return new DummyJsonTaskSyncFetcherByWebClient();
    }
}
