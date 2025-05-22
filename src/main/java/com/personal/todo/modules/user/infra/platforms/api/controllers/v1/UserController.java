package com.personal.todo.modules.user.infra.platforms.api.controllers.v1;

import com.personal.todo.modules.auth.business.entities.AuthUser;
import com.personal.todo.modules.task.business.app.params.output.ReadRemoteTasksOutput;
import com.personal.todo.modules.task.business.app.actions.TaskActions;
import com.personal.todo.modules.user.business.app.UserActions;
import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.user.business.entities.User;
import com.personal.todo.modules.task.infra.platforms.api.controllers.v1.responses.RemoteTaskApi;
import com.personal.todo.modules.task.infra.platforms.api.controllers.v1.responses.RemoteTasksApiResponse;
import com.personal.todo.modules.user.infra.platforms.api.controllers.v1.responses.UserApi;
import com.personal.todo.modules.user.infra.platforms.api.controllers.v1.responses.UserApiResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserActions userActions;
    @Autowired
    private TaskActions taskActions;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


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

    @GetMapping("{id}/tasks/remote")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RemoteTasksApiResponse> readAllRemoteTasksByUserId(
            @PathVariable @NotNull Integer id,
            Authentication auth
    ) {
        String authEmail = ((AuthUser) auth.getPrincipal()).getUsername();
        User user = userActions.readByEmail(authEmail);

        if (!user.getId().equals(id)) {
            logger.atWarn()
                    .setMessage("auth user id does not match the path user id")
                    .addKeyValue("authUserId", user.getId())
                    .addKeyValue("pathUserId", id)
                    .log();


            throw new IllegalArgumentException("auth user id does not match the path user id");
        }

        ReadRemoteTasksOutput output = taskActions.readRemoteTasksByUserId(id);

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
