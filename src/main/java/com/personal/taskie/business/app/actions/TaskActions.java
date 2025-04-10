package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.TaskRepository;
import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateTaskParams;
import com.personal.taskie.business.app.params.UpdateTaskParams;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TaskActions {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task create(CreateTaskParams params) {
        User user = userRepository.findById(params.userId())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        Task task = params.createTask(user);

        return taskRepository.save(task);
    }

    public Task readById(int id) {
        if (id < 1) throw new RuntimeException("id could not be null");

        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found!"));
    }

    public List<Task> readAll() {
        return null;
    }

    public List<Task> readAllByUserId(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        return taskRepository.findAllByUserId(userId);
    }

    public Task update(UpdateTaskParams params) {
        Task task = taskRepository.findById(params.id())
                .orElseThrow(() -> new EntityNotFoundException("todo not found"));

        task.setTitle(params.title());
        task.setStatus(params.status());

        return taskRepository.save(task);
    }
}
