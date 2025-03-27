package com.personal.todo.business.app.dtos;

import lombok.NonNull;

public record CreateTodoParams(@NonNull String title, int userId) {
}
