package com.personal.todo.modules.task.business.app.ports.output.remotetask;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface RemoteTaskSyncFetcher {
    RemoteTasksResponse fetchTasksByUserId(@NotNull @Positive Integer userId);
}
