package com.personal.task.infra.platforms.api.responses;

import jakarta.validation.Valid;

import java.util.List;

public record RemoteTasksApiResponse(
        List<RemoteTaskApi> tasks,
        int total,
        int skip,
        int limit,
        @Valid
        UserApi user
) {
}
