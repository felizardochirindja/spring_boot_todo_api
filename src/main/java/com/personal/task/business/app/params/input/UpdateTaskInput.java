package com.personal.task.business.app.params.input;

import com.personal.task.business.entities.Task;
import com.personal.task.business.entities.User;
import com.personal.task.business.types.TaskStatus;
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
