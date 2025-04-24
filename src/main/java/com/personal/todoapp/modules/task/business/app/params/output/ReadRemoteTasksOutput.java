package com.personal.todoapp.modules.task.business.app.params.output;

import com.personal.todoapp.modules.user.business.entities.User;
import com.personal.todoapp.modules.task.business.app.ports.output.remotetask.RemoteTask;

import java.util.List;

public record ReadRemoteTasksOutput(
        List<RemoteTask> tasks,
        int total,
        int skip,
        int limit,
        User user
) {
}
