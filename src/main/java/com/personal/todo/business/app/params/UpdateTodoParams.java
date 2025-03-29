package com.personal.todo.business.app.params;

import io.micrometer.common.lang.NonNull;

public record UpdateTodoParams(int id, @NonNull String title) {
}
