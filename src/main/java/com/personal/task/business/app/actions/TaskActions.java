package com.personal.task.business.app.actions;

import com.personal.task.adapters.repos.TaskRepository;
import com.personal.task.adapters.repos.UserRepository;
import com.personal.task.business.app.params.output.ReadRemoteTasksOutput;
import com.personal.task.business.entities.exceptions.EntityNotFoundException;
import com.personal.task.business.app.params.input.CreateTaskInput;
import com.personal.task.business.app.params.input.UpdateTaskInput;
import com.personal.task.business.app.ports.output.remotetask.RemoteTaskSyncFetcher;
import com.personal.task.business.entities.Task;
import com.personal.task.business.entities.User;
import com.personal.task.business.app.ports.output.remotetask.RemoteTasksResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskActions {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RemoteTaskSyncFetcher remoteTaskSyncFetcher;
    private final static Logger logger = LoggerFactory.getLogger(TaskActions.class.getName());

    @CacheEvict(value = "tasks", key = "#params.userId()")
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

    @Cacheable(value = "task", key = "#id", unless = "#result == null")
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

    @Cacheable(value = "tasks", key = "#userId", unless = "#result.isEmpty()")
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

    @CacheEvict(value = "tasks", key = "#result.getUser().getId()")
    @CachePut(value = "task", key = "#params.id()")
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
        logger.atInfo()
                .setMessage("Reading remote tasks by user id!")
                .addKeyValue("userId", userId)
                .log();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("User not found!")
                            .addKeyValue("userId", userId)
                            .log();

                    return new EntityNotFoundException("user not found");
                });

        RemoteTasksResponse response = remoteTaskSyncFetcher.fetchTasksByUserId(userId);

        logger.atInfo()
                .setMessage("Remote tasks read!")
                .addKeyValue("userId", userId)
                .addKeyValue("taskCount", response.todos().size())
                .log();

        return new ReadRemoteTasksOutput(
                response.todos(),
                response.total(),
                response.skip(),
                response.limit(),
                user
        );
    }
}
