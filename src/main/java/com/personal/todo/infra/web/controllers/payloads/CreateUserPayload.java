package com.personal.todo.infra.web.controllers.payloads;

import jakarta.validation.constraints.NotNull;

public record CreateUserPayload(
        @NotNull(message = "name cannot be null")
        String name,
        @NotNull(message = "email cannot be null")
        String email,
        @NotNull(message = "password cannot be null")
        String password
) {
}
