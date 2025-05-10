package com.personal.todo.modules.task.business.app.params.input;

import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.user.business.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskInput(
        @NotNull
        @NotBlank
        String title,
        int userId
) {
    public Task createTask(User user) {
        return new Task(title, user);
    }
}
