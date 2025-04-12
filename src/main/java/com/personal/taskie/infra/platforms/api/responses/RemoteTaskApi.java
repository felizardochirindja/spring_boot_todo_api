package com.personal.taskie.infra.platforms.api.responses;

import com.personal.taskie.business.app.ports.output.remotetask.RemoteTask;
import com.personal.taskie.business.types.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RemoteTaskApi {
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
