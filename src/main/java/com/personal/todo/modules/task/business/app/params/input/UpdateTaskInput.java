package com.personal.todo.modules.task.business.app.params.input;

import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.user.business.entities.User;
import com.personal.todo.modules.task.business.types.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskInput(
        int id,
        @NotNull
        @NotBlank
        String title,
        @NotNull
        TaskStatus status
) {
        public Task createTask(User user) {
                return new Task(id, title, status, user);
        }
}
