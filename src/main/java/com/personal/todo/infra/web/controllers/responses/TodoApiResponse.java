package com.personal.todo.infra.web.controllers.responses;

import com.personal.todo.business.entities.Todo;

public record TodoApiResponse(
        String status,
        String message,
        Todo todo
) {
}
