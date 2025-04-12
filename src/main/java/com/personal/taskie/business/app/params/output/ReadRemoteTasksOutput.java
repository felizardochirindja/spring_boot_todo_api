package com.personal.taskie.business.app.params.output;

import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.app.ports.output.remotetask.RemoteTask;

import java.util.List;

public record ReadRemoteTasksOutput(
        List<RemoteTask> tasks,
        int total,
        int skip,
        int limit,
        User user
) {
}
