package com.personal.todo.infra.web.controllers.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserPayload(
        @NotNull(message = "name cannot be null")
        @NotBlank
        String name,
        @NotNull(message = "email cannot be null")
        @NotBlank
        String email,
        @NotNull(message = "password cannot be null")
        @NotBlank
        String password
) {
}
