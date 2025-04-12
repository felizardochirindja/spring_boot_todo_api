package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.app.ports.output.remotetask.RemoteTask;

import java.util.List;

public record ReadRemoteTaskOutput(
        List<RemoteTask> todos,
        int total,
        int skip,
        int limit,
        User user
) {
}
