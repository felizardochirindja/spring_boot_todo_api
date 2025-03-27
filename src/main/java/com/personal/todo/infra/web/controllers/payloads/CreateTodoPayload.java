package com.personal.todo.infra.web.controllers.payloads;

public record CreateTodoPayload(String title, int userId) {
}
