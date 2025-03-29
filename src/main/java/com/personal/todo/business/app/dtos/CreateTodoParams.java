package com.personal.todo.business.app.dtos;

import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;
import com.personal.todo.business.types.TodoStatus;
import lombok.NonNull;

public record CreateTodoParams(@NonNull String title, int userId) {
    public Todo createTodo(TodoStatus status, User user) {
        return new Todo(title, status, user);
    }
}
