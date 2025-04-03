package com.personal.taskie.infra.platforms.api.responses;

import com.personal.taskie.business.entities.Task;
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

    public static TodoResponse fromEntity(Task task) {
        return new TodoResponse(task.getTitle(), task.getStatus());
    }
}
