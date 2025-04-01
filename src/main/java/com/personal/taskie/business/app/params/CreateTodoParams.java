package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.Todo;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;
import lombok.NonNull;

public record CreateTodoParams(@NonNull String title, int userId) {
    public Todo createTodo(TodoStatus status, User user) {
        return new Todo(title, status, user);
    }
}
