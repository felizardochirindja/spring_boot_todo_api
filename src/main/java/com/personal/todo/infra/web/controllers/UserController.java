package com.personal.todo.infra.web.controllers;

import com.personal.todo.business.app.UserActions;
import com.personal.todo.business.app.dtos.CreateUserParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;
import com.personal.todo.infra.web.controllers.payloads.CreateUserPayload;
import com.personal.todo.infra.web.controllers.responses.UserApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserActions userActions;

    @PostMapping
    public ResponseEntity<UserApiResponse> create(@RequestBody @Valid CreateUserPayload payload) {
        CreateUserParams params = new CreateUserParams(
            payload.name(),
            payload.email(),
            payload.password()
        );

        User user = userActions.create(params);
        UserApiResponse response = new UserApiResponse(
                "sucess",
                "user created successfully",
                user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserApiResponse> readById(@PathVariable Integer id) {
        User user = userActions.readById(id);

        UserApiResponse response = new UserApiResponse(
                "sucess",
                "user read sucessfully",
                user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public Todo update() {
        return null;
    }
}
