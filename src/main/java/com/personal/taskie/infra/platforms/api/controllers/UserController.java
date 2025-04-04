package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.business.app.actions.TaskActions;
import com.personal.taskie.business.app.actions.UserActions;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.platforms.api.responses.UserApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserResponse;
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
                UserResponse.fromEntity(user)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> readAllTasks(@PathVariable Integer id) {
        return ResponseEntity.ok(taskActions.readAllByUserId(id));
    }
}
