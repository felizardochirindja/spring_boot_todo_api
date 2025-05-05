package com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses;

import com.personal.todoapp.modules.user.infra.platforms.api.controllers.v1.responses.UserApi;
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
