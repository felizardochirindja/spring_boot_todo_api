package com.personal.todo.modules.task.infra.platforms.api.controllers.v1.responses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record TaskApiResponse(
        @NotNull
        String status,
        @NotNull
        String message,
        @NotNull
        @Valid
        TaskApi task
) {
}
