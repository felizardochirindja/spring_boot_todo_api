package com.personal.task.infra.platforms.api.payloads;

import com.personal.task.business.app.params.input.UpdateTaskInput;
import com.personal.task.business.types.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskPayload(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        TaskStatus status
) {
    public UpdateTaskInput createActionParams(int taskId) {
        return new UpdateTaskInput(taskId, title, status);
    }
}
