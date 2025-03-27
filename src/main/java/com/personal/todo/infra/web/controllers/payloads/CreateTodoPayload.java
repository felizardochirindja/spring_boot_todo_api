package com.personal.todo.infra.web.controllers.payloads;

import com.personal.todo.business.app.dtos.CreateTodoParams;

import java.util.Objects;

public record CreateTodoPayload(String title, int userId) {
    public CreateTodoPayload {
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(userId, "userId cannot be null");
    }
}
