package com.personal.todo.modules.task.infra.adapters.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTasksResponse;

@Component
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @CachePut(value = "remoteTasks", key = "#userId")
    public RemoteTasksResponse cacheSuccess(Integer userId, RemoteTasksResponse response) {
        logger.atInfo()
                .setMessage("Caching successful response for fetchTasksByUserId")
                .addKeyValue("userId", userId)
                .log();

        return response;
    }
}
