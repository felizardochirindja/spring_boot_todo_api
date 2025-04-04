package com.personal.taskie.business.app.params;

import com.personal.taskie.business.types.TodoStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskParams(
        int id,
        @NotNull
        String title,
        @NotNull
        TodoStatus status
) {
}
