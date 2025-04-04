package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.UpdateTaskParams;
import com.personal.taskie.business.types.TodoStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskPayload(
        @NotNull
        String title,
        @NotNull
        TodoStatus status
) {
    public UpdateTaskParams createActionParams(int taskId) {
        return new UpdateTaskParams(taskId, title, status);
    }
}
