package com.personal.todo.infra.web.controllers;

import com.personal.todo.adapters.repo.TodoRepository;
import com.personal.todo.business.app.TodoActions;
import com.personal.todo.business.app.dtos.CreateTodoParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.infra.web.controllers.payloads.CreateTodoPayload;
import com.personal.todo.infra.web.controllers.responses.CreateTodoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoActions actions;

    @PostMapping
    public ResponseEntity<CreateTodoResponse> create(@RequestBody @Valid CreateTodoPayload payload) {
        CreateTodoParams params = new CreateTodoParams(payload.title(), payload.userId());

        CreateTodoResponse response = new CreateTodoResponse(
                "sucess",
                "todo created sucessfully",
                actions.create(params)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
