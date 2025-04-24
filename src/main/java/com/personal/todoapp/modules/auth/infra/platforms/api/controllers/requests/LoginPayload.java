package com.personal.todoapp.modules.auth.infra.platforms.api.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginPayload(
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password
) {
}
