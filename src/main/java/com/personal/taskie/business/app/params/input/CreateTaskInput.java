package com.personal.taskie.business.app.params.input;

import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
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
