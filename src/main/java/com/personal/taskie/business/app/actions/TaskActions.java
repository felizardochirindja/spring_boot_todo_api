package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.TaskRepository;
import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.output.ReadRemoteTasksOutput;
import com.personal.taskie.business.entities.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.input.CreateTaskInput;
import com.personal.taskie.business.app.params.input.UpdateTaskInput;
import com.personal.taskie.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.app.ports.output.remotetask.RemoteTasksResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TaskActions {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RemoteTaskSyncFetcher remoteTaskSyncFetcher;
    private final static Logger logger = LoggerFactory.getLogger(TaskActions.class.getName());

    public Task create(CreateTaskInput params) {
        logger.atInfo()
                .setMessage("Creating task!")
                .addKeyValue("userId", params.userId())
                .log();

        User user = userRepository.findById(params.userId())
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("User not found!")
                            .addKeyValue("userId", params.userId())
                            .log();

                    return new EntityNotFoundException("user not found");
                });

        Task createdTask = taskRepository.save(params.createTask(user));
        logger.atInfo()
                .setMessage("Task created!")
                .addKeyValue("taskId", createdTask.getId())
                .addKeyValue("userId", createdTask.getUser().getId())
                .log();
            
        return createdTask;
    }

    public Task readById(int id) {
        logger.atInfo()
                .setMessage("Reading task!")
                .addKeyValue("taskId", id)
                .log();

        if (id < 1) {
            logger.atError()
                    .setMessage("task id could not be null!")
                    .log();

            throw new RuntimeException("task id could not be null");
        }

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("task not found!")
                            .addKeyValue("taskId", id)
                            .log();

                    return new EntityNotFoundException("task not found!");
                });

        logger.atInfo()
                .setMessage("Task read!")
                .addKeyValue("taskId", task.getId())
                .addKeyValue("userId", task.getUser().getId())
                .log();

        return task;
    }

    public List<Task> readAllByUserId(int userId) {
        logger.atInfo()
                .setMessage("Reading all tasks by user id!")
                .addKeyValue("userId", userId)
                .log();

        userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("User not found!")
                            .addKeyValue("userId", userId)
                            .log();

                    return new EntityNotFoundException("user not found");
                });

        List<Task> task = taskRepository.findAllByUserId(userId);

        logger.atInfo()
                .setMessage("Tasks read!")
                .addKeyValue("userId", userId)
                .addKeyValue("taskCount", task.size())
                .log();

        return task;
    }

    public Task update(UpdateTaskInput params) {
        logger.atInfo()
                .setMessage("Updating task!")
                .addKeyValue("taskId", params.id())
                .log();

        Task existingTask = taskRepository.findById(params.id())
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("task not found!")
                            .addKeyValue("taskId", params.id())
                            .log();

                    return new EntityNotFoundException("todo not found");
                });

        Task task = taskRepository.save(params.createTask(existingTask.getUser()));
        
        logger.atInfo()
                .setMessage("Task updated!")
                .addKeyValue("taskId", task.getId())
                .addKeyValue("userId", task.getUser().getId())
                .log();

        return task;
    }

    public ReadRemoteTasksOutput readRemoteTasksByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        RemoteTasksResponse response = remoteTaskSyncFetcher.fetchTasksByUserId(userId);

        return new ReadRemoteTasksOutput(
                response.todos(),
                response.total(),
                response.skip(),
                response.limit(),
                user
        );
    }
}
