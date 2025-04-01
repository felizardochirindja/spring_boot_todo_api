package com.personal.taskie.infra.platforms.api.responses;

import com.personal.taskie.business.entities.Todo;
import com.personal.taskie.business.types.TodoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TodoResponse {
    @NotNull
    private String title;
    @NotNull
    private TodoStatus status;

    public static TodoResponse fromEntity(Todo todo) {
        return new TodoResponse(todo.getTitle(), todo.getStatus());
    }
}
