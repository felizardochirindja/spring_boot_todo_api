package com.personal.todo.business.app.dtos;

import io.micrometer.common.lang.NonNull;

public record UpdateTodoParams(int id, @NonNull String title) {
}
