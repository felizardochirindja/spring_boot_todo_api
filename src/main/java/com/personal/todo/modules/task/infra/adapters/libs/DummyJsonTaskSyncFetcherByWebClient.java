package com.personal.todo.modules.task.infra.adapters.libs;

import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTasksResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache;

@Component
public class DummyJsonTaskSyncFetcherByWebClient implements RemoteTaskSyncFetcher {
    private static final Logger logger = LoggerFactory.getLogger(DummyJsonTaskSyncFetcherByWebClient.class);
    @Autowired
    private WebClient dummyJsonWebClient;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CacheService cacheService;

    @CircuitBreaker(name = "fetchTasksByUserId", fallbackMethod = "fetchTasksByUserIdFallback")
    public RemoteTasksResponse fetchTasksByUserId(Integer userId) {
        var response = dummyJsonWebClient.get()
                .uri("/todos/user/" + userId)
                .retrieve()
                .bodyToMono(RemoteTasksResponse.class)
                .block();

        cacheService.cacheSuccess(userId, response);

        return response;
    }

    public RemoteTasksResponse fetchTasksByUserIdFallback(Integer userId, Throwable throwable) {
        logger.atWarn()
            .setMessage("Fallback triggered for fetchTasksByUserId due to circuit breaker exception!")
            .addKeyValue("userId", userId)
            .addKeyValue("exception", throwable.getClass().getSimpleName())
            .addKeyValue("message", throwable.getMessage())
            .log();

        Cache cache = cacheManager.getCache("remoteTasks");
        RemoteTasksResponse cachedRemoteTasks = null;

        if (cache != null) {
            cachedRemoteTasks = cache.get(userId, RemoteTasksResponse.class);
        }

        if (cachedRemoteTasks == null) {
            logger.atWarn()
                .setMessage("cache miss for fetchTasksByUserId, returning empty response")
                .addKeyValue("userId", userId)
                .log();

            return new RemoteTasksResponse(List.of(), 0, 0, 0, userId);
        }

        logger.atWarn()
            .setMessage("cache hit for fetchTasksByUserId")
            .addKeyValue("userId", userId)
            .addKeyValue("exception", throwable.getClass().getSimpleName())
            .addKeyValue("message", throwable.getMessage())
            .log();

        return cachedRemoteTasks;
    }
}
