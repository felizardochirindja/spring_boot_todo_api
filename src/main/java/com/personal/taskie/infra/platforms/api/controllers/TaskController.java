package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.business.app.actions.TaskActions;
import com.personal.taskie.business.app.params.CreateTodoParams;
import com.personal.taskie.business.app.params.UpdateTodoParams;
import com.personal.taskie.infra.platforms.api.payloads.CreateTodoPayload;
import com.personal.taskie.infra.platforms.api.responses.TaskApiResponse;
import com.personal.taskie.infra.platforms.api.responses.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    @Autowired
    private TaskActions actions;

    @PostMapping
    public ResponseEntity<TaskApiResponse> create(@RequestBody @Valid CreateTodoPayload payload) {
        CreateTodoParams params = payload.createActionParams();

        TaskApiResponse response = new TaskApiResponse(
                "success",
                "todo created successfully",
                TodoResponse.fromEntity(actions.create(params))
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskApiResponse> readById(@PathVariable Integer id) {
        var task = actions.readById(id);

        TaskApiResponse response = new TaskApiResponse(
                "sucess",
                "task read successfully",
                TodoResponse.fromEntity(task)
        );

        return ResponseEntity.ok().body(response);
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
    public ResponseEntity<TaskApiResponse> update(@RequestParam String title, @PathVariable Integer id) {
        UpdateTodoParams params = new UpdateTodoParams(id, title);

        TaskApiResponse response = new TaskApiResponse(
                "success",
                "task updated successfuly",
                TodoResponse.fromEntity(actions.update(params))
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
