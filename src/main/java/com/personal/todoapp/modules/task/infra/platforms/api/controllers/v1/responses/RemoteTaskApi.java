package com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses;

import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTask;
import com.personal.todoapp.modules.task.business.types.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class RemoteTaskApi {
    @NotNull
    private String todo;
    @NotNull
    private TaskStatus status;

    public static RemoteTaskApi fromEntity(RemoteTask task) {
        return new RemoteTaskApi(
                task.todo(),
                task.completed() ? TaskStatus.COMPLETED : TaskStatus.PENDING
        );
    }
}
