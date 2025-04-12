package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.ReadRemoteTaskOutput;
import com.personal.taskie.business.app.actions.TaskActions;
import com.personal.taskie.business.app.actions.UserActions;
import com.personal.taskie.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.platforms.api.responses.RemoteTaskApi;
import com.personal.taskie.infra.platforms.api.responses.RemoteTasksApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserApi;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public final class UserController {
    @Autowired
    private UserActions userActions;
    @Autowired
    private TaskActions taskActions;
    @Autowired
    private RemoteTaskSyncFetcher remoteTaskSyncFetcher;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserApiResponse> readById(@PathVariable Integer id) {
        User user = userActions.readById(id);

        UserApiResponse response = new UserApiResponse(
                "sucess",
                "user read successfully",
                UserApi.fromEntity(user)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> readAllTasks(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok(taskActions.readAllByUserId(id));
    }

    @GetMapping("/tasks/remote")
    public ResponseEntity<RemoteTasksApiResponse> readAllRemoteTasksByUserId() {
        int userId = 1;

        ReadRemoteTaskOutput output = taskActions.readRemoteTasksByUserId(userId);

        List<RemoteTaskApi> taskApiList = output.todos()
                .stream()
                .map(RemoteTaskApi::fromEntity)
                .toList();

        var response = new RemoteTasksApiResponse(
                taskApiList,
                output.total(),
                output.skip(),
                output.limit(),
                UserApi.fromEntity(output.user())
        );

        return ResponseEntity.ok(response);
    }
}
