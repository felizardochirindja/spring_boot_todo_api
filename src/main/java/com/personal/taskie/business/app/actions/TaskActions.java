package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.TaskRepository;
import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateTodoParams;
import com.personal.taskie.business.app.params.UpdateTodoParams;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TaskActions {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task create(CreateTodoParams params) {
        User user = userRepository.findById(params.userId())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        Task task = params.createTodo(TodoStatus.PENDING, user);

        return taskRepository.save(task);
    }

    public Task readById(String id) {
        return null;
    }

    public List<Task> readAll() {
        return null;
    }

    public Task update(UpdateTodoParams params) {
        Task task = taskRepository.findById(params.id())
                .orElseThrow(() -> new EntityNotFoundException("todo not found"));

        return null;
    }
}
