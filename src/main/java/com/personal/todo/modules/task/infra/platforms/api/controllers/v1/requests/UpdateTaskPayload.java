package com.personal.todo.modules.task.infra.platforms.api.controllers.v1.requests;

import com.personal.todo.modules.task.business.app.params.input.UpdateTaskInput;
import com.personal.todo.modules.task.business.types.TaskStatus;
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
