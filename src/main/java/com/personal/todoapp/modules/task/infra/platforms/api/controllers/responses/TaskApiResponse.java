package com.personal.todoapp.modules.task.infra.platforms.api.controllers.responses;

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
