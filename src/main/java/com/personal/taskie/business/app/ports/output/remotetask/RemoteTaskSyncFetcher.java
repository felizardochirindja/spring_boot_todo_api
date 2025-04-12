package com.personal.taskie.business.app.ports.output;

import com.personal.taskie.infra.adapters.libs.dummyjson.RemoteTasksResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface RemoteTaskSyncFetcher {
    RemoteTasksResponse fetchTasksByUserId(@NotNull @Positive Integer userId);
}
