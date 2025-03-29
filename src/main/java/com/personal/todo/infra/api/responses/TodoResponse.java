package com.personal.todo.infra.api.responses;

import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.types.TodoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponse {
    private String title;
    private TodoStatus status;

    public static TodoResponse fromEntity(Todo todo) {
        return new TodoResponse(todo.getTitle(), todo.getStatus());
    }
}
