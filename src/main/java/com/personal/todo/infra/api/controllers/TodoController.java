package com.personal.todo.infra.api.controllers;

import com.personal.todo.business.app.actions.TodoActions;
import com.personal.todo.business.app.params.CreateTodoParams;
import com.personal.todo.business.app.params.UpdateTodoParams;
import com.personal.todo.infra.api.payloads.CreateTodoPayload;
import com.personal.todo.infra.api.responses.TodoApiResponse;
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
    public ResponseEntity<TodoApiResponse> create(@RequestBody @Valid CreateTodoPayload payload) {
        CreateTodoParams params = payload.createActionParams();

        TodoApiResponse response = new TodoApiResponse(
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
    public String readAllByUserId() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoApiResponse> update(@RequestParam String title, @PathVariable Integer id) {
        UpdateTodoParams params = new UpdateTodoParams(id, title);

        TodoApiResponse response = new TodoApiResponse(
                "success",
                "todo updated successfuly",
                actions.update(params)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
