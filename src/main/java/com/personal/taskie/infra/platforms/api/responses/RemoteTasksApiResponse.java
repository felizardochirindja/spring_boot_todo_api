package com.personal.taskie.infra.platforms.api.responses;

import com.personal.taskie.infra.adapters.libs.dummyjson.RemoteTask;

import java.util.List;

public record RemoteTaskApiResponse(
        List<RemoteTask> todos,
        int total,
        int skip,
        int limit,
        UserResponse user
) {
}
