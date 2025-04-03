package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.business.app.actions.TaskActions;
import com.personal.taskie.business.app.params.CreateTodoParams;
import com.personal.taskie.business.app.params.UpdateTodoParams;
import com.personal.taskie.infra.platforms.api.payloads.CreateTodoPayload;
import com.personal.taskie.infra.platforms.api.responses.TodoApiResponse;
import com.personal.taskie.infra.platforms.api.responses.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public final class TaskController {
    @Autowired
    private TaskActions actions;

    @PostMapping
    public ResponseEntity<TodoApiResponse> create(@RequestBody @Valid CreateTodoPayload payload) {
        CreateTodoParams params = payload.createActionParams();

        TodoApiResponse response = new TodoApiResponse(
                "success",
                "todo created successfully",
                TodoResponse.fromEntity(actions.create(params))
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
                TodoResponse.fromEntity(actions.update(params))
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
