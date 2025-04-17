package com.personal.task.infra.platforms.api.controllers;

import com.personal.task.business.app.actions.TaskActions;
import com.personal.task.business.app.params.input.CreateTaskInput;
import com.personal.task.infra.platforms.api.payloads.CreateTaskPayload;
import com.personal.task.infra.platforms.api.payloads.UpdateTaskPayload;
import com.personal.task.infra.platforms.api.responses.TaskApiResponse;
import com.personal.task.infra.platforms.api.responses.TaskApi;
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
    public ResponseEntity<TaskApiResponse> create(@RequestBody @Valid CreateTaskPayload payload) {
        CreateTaskInput params = payload.createActionParams();

        TaskApiResponse response = new TaskApiResponse(
                "success",
                "todo created successfully",
                TaskApi.fromEntity(actions.create(params))
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskApiResponse> readById(@PathVariable Integer id) {
        var task = actions.readById(id);

        TaskApiResponse response = new TaskApiResponse(
                "sucess",
                "task read successfully",
                TaskApi.fromEntity(task)
        );

        return ResponseEntity.ok().body(response);
    }

    public String delete() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskApiResponse> update(@PathVariable Integer id, @RequestBody UpdateTaskPayload payload) {
        var params = payload.createActionParams(id);

        TaskApiResponse response = new TaskApiResponse(
                "success",
                "task updated successfuly",
                TaskApi.fromEntity(actions.update(params))
        );

        return ResponseEntity.ok(response);
    }
}
