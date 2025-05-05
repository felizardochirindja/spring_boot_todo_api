package com.personal.todoapp.modules.user.infra.platforms.api.controllers.v1;

import com.personal.todoapp.modules.task.business.app.params.output.ReadRemoteTasksOutput;
import com.personal.todoapp.modules.task.business.app.actions.TaskActions;
import com.personal.todoapp.modules.user.business.app.UserActions;
import com.personal.todoapp.modules.task.business.entities.Task;
import com.personal.todoapp.modules.user.business.entities.User;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses.RemoteTaskApi;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses.RemoteTasksApiResponse;
import com.personal.todoapp.modules.user.infra.platforms.api.controllers.v1.responses.UserApi;
import com.personal.todoapp.modules.user.infra.platforms.api.controllers.v1.responses.UserApiResponse;
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

        ReadRemoteTasksOutput output = taskActions.readRemoteTasksByUserId(userId);

        List<RemoteTaskApi> taskList = output.tasks()
                .stream()
                .map(RemoteTaskApi::fromEntity)
                .toList();

        var response = new RemoteTasksApiResponse(
                taskList,
                output.total(),
                output.skip(),
                output.limit(),
                UserApi.fromEntity(output.user())
        );

        return ResponseEntity.ok(response);
    }
}
