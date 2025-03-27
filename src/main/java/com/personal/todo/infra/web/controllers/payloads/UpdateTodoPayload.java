package com.personal.todo.infra.web.controllers.payloads;

public record UpdateTodoPayload(String id, String title, String userId) {
}
