package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.input.UpdateTaskInput;
import com.personal.taskie.business.types.TaskStatus;
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
