package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import lombok.NonNull;

public record CreateTaskParams(@NonNull String title, int userId) {
    public Task createTask(User user) {
        return new Task(title, user);
    }
}
