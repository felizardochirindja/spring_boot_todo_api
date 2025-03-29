package com.personal.todo.infra.api.responses;

public record TodoApiResponse(
        String status,
        String message,
        TodoResponse todo
) {
}
