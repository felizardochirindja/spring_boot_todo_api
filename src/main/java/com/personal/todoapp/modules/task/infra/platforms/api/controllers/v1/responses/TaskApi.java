package com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses;

import com.personal.todoapp.modules.task.business.entities.Task;
import com.personal.todoapp.modules.task.business.types.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class TaskApi {
    @NotNull
    private String title;
    @NotNull
    private TaskStatus status;

    public static TaskApi fromEntity(Task task) {
        return new TaskApi(task.getTitle(), task.getStatus());
    }
}
