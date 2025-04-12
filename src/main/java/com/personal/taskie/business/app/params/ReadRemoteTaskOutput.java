package com.personal.taskie.business.app;

import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.adapters.libs.dummyjson.RemoteTask;

import java.util.List;

public record ReadRemoteTaskOutput(
        List<RemoteTask> todos,
        int total,
        int skip,
        int limit,
        User user
) {
}
