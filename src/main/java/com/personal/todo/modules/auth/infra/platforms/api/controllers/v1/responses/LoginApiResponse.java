package com.personal.todo.modules.auth.infra.platforms.api.controllers.v1.responses;

import jakarta.validation.constraints.NotNull;

public record LoginApiResponse(
        @NotNull
        String status,
        @NotNull
        String message,
        @NotNull
        String token
) {
}
