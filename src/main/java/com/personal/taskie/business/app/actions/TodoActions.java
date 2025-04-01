package com.personal.todo.business.app.actions;

import com.personal.todo.adapters.repos.TodoRepository;
import com.personal.todo.adapters.repos.UserRepository;
import com.personal.todo.business.app.exceptions.EntityNotFoundException;
import com.personal.todo.business.app.params.CreateTodoParams;
import com.personal.todo.business.app.params.UpdateTodoParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;
import com.personal.todo.business.types.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TodoActions {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public Todo create(CreateTodoParams params) {
        User user = userRepository.findById(params.userId())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        Todo todo = params.createTodo(TodoStatus.PENDING, user);

        return todoRepository.save(todo);
    }

    public Todo readById(String id) {
        return null;
    }

    public List<Todo> readAll() {
        return null;
    }

    public Todo update(UpdateTodoParams params) {
        Todo todo = todoRepository.findById(params.id())
                .orElseThrow(() -> new EntityNotFoundException("todo not found"));

        return null;
    }
}
