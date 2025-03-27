package com.personal.todo.business.app.dtos;

import java.util.Objects;

public record CreateTodoParams(String title, int userId) {
    public CreateTodoParams {
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(userId, "userId cannot be null");
    }
}
