package com.personal.todo.modules.task.infra.adapters.libs;

import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTasksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public final class DummyJsonTaskSyncFetcherByWebClient implements RemoteTaskSyncFetcher {
    @Autowired
    private WebClient dummyJsonWebClient;

    public RemoteTasksResponse fetchTasksByUserId(Integer userId) {
        return dummyJsonWebClient.get()
                .uri("/todos/user/" + userId)
                .retrieve()
                .bodyToMono(RemoteTasksResponse.class)
                .block();
    }
}
