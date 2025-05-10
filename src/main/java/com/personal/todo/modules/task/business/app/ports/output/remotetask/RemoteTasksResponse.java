package com.personal.todo.modules.task.business.app.ports.output.remotetask;

import java.util.List;

public record RemoteTasksResponse(
        List<RemoteTask> todos,
        int total,
        int skip,
        int limit,
        int userId
) {
}
