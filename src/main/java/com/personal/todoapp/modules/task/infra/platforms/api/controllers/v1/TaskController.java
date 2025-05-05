package com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1;

import com.personal.todoapp.modules.task.business.app.actions.TaskActions;
import com.personal.todoapp.modules.task.business.app.params.input.CreateTaskInput;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.requests.CreateTaskPayload;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.requests.UpdateTaskPayload;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses.TaskApiResponse;
import com.personal.todoapp.modules.task.infra.platforms.api.controllers.v1.responses.TaskApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "create new task",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "todo created successfully!",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = TaskApiResponse.class)
                        )
                ),
            }
    )
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
