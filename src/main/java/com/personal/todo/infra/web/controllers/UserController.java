package com.personal.todo.infra.web.controllers;

import com.personal.todo.business.app.UserActions;
import com.personal.todo.business.app.dtos.CreateTodoParams;
import com.personal.todo.business.app.dtos.CreateUserParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;
import com.personal.todo.infra.web.controllers.payloads.CreateUserPayload;
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
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserPayload payload) {
        CreateUserParams params = new CreateUserParams(
                payload.name(),
                payload.email(),
                payload.password()
        );

        User user = userActions.create(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public String readById() {
        return null;
    }

    @DeleteMapping
    public String delete() {
        return null;
    }

    @GetMapping
    public String readAll() {
        return null;
    }

    @PutMapping
    public Todo update() {
        return null;
    }
}
