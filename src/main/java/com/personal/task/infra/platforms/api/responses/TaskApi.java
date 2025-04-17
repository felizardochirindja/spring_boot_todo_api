package com.personal.task.infra.platforms.api.responses;

import com.personal.task.business.entities.Task;
import com.personal.task.business.types.TaskStatus;
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
