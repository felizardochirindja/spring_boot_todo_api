package com.personal.todoapp.modules.task.infra.adapters.libs;

import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTasksResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public final class DummyJsonTaskSyncFetcherByWebClient implements RemoteTaskSyncFetcher {
    private final WebClient webClient = WebClient.create("https://dummyjson.com");

    public RemoteTasksResponse fetchTasksByUserId(Integer userId) {
        return webClient.get()
                .uri("/todos/user/" + userId)
                .retrieve()
                .bodyToMono(RemoteTasksResponse.class)
                .block();
    }
}
