package com.personal.task.infra.platforms.api.responses;

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
