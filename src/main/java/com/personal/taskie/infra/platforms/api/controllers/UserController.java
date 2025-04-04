package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.business.app.actions.UserActions;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.platforms.api.responses.UserApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public final class UserController {
    @Autowired
    private UserActions userActions;

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
    public ResponseEntity readAllTasks() {
        return null;
    }
}
