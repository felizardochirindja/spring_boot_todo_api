package com.personal.todoapp.modules.task.infra.platforms.api.controllers.requests;

import com.personal.todoapp.modules.task.business.app.params.input.UpdateTaskInput;
import com.personal.todoapp.modules.task.business.types.TaskStatus;
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
