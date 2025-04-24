package com.personal.todoapp.modules.user.infra.platforms.api.controllers.responses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserApiResponse(
        @NotNull
        String status,
        @NotNull
        String message,
        @NotNull
        @Valid
        UserApi user
) {
}
