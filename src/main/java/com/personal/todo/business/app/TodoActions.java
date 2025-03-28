package com.personal.todo.business.app;

import com.personal.todo.adapters.repo.TodoRepository;
import com.personal.todo.adapters.repo.UserRepository;
import com.personal.todo.business.app.dtos.CreateTodoParams;
import com.personal.todo.business.app.dtos.UpdateTodoParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;
import com.personal.todo.business.types.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoActions {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public Todo create(CreateTodoParams params) {
        User user = userRepository.findById(params.userId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        System.out.println(user);

        Todo todo = new Todo(params.title(), TodoStatus.PENDENDING, user);

        return todoRepository.save(todo);
    }

    public Todo readById(String id) {
        return null;
    }

    public Todo delete(String todoId, String userId) {
        return null;
    }

    public List<Todo> readAll() {
        return null;
    }

    public Todo update(UpdateTodoParams params) {
        return null;
    }
}
