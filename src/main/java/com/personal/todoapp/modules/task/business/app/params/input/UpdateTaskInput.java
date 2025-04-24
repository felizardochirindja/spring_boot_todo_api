package com.personal.todoapp.modules.task.business.app.params.input;

import com.personal.todoapp.modules.task.business.entities.Task;
import com.personal.todoapp.modules.user.business.entities.User;
import com.personal.todoapp.modules.task.business.types.TaskStatus;
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
