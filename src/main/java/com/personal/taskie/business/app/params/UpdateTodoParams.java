package com.personal.taskie.business.app.params;

import jakarta.validation.constraints.NotNull;

public record UpdateTodoParams(int id, @NotNull String title) {
}
