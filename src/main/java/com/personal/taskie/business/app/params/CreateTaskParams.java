package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;
import lombok.NonNull;

public record CreateTaskParams(@NonNull String title, int userId) {
    public Task createTodo(TodoStatus status, User user) {
        return new Task(title, status, user);
    }
}
