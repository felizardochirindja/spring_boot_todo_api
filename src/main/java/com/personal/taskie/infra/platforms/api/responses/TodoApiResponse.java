package com.personal.todo.infra.platforms.api.responses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record TodoApiResponse(
        @NotNull
        String status,
        @NotNull
        String message,
        @NotNull
        @Valid
        TodoResponse todo
) {
}
