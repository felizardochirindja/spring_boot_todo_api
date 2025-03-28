package com.personal.todo.infra.web.controllers.payloads;

import jakarta.validation.constraints.NotNull;

public record CreateTodoPayload(
        @NotNull(message = "title cannot be null")
        String title,
        @NotNull(message = "userId cannot be null")
        Integer userId
) {
}
