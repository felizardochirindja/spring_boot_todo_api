package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskParams(
        int id,
        @NotNull
        String title,
        @NotNull
        TodoStatus status
) {
        public Task createTask(User user) {
                return new Task(id, title, status, user);
        }
}
