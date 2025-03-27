package com.personal.todo.business.app;

import com.personal.todo.adapters.repo.TodoRepository;
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

    public Todo create(CreateTodoParams params) {
        User user = new User("felix", "felix@gmail.com", "1234");
        return new Todo (params.title(), TodoStatus.PENDENDING, user);
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
