package com.personal.todo.business.app.dtos;

import java.util.Objects;

public record UpdateTodoParams(String id, String title, String userId) {
    public UpdateTodoParams {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(id, "title cannot be null");
        Objects.requireNonNull(id, "userId cannot be null");
    }
}
