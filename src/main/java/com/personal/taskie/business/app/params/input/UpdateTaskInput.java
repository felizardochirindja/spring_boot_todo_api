package com.personal.taskie.business.app.params.input;

import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TaskStatus;
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
