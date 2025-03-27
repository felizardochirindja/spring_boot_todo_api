package com.personal.todo.infra.web.controllers.payloads;

import java.util.Objects;

public record UpdateTodoPayload(String id, String title, String userId) {
    public UpdateTodoPayload {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(id, "title cannot be null");
        Objects.requireNonNull(id, "userId cannot be null");
    }
}
