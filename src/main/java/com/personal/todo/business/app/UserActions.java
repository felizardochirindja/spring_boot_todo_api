package com.personal.todo.business.app;

import com.personal.todo.adapters.repo.UserRepository;
import com.personal.todo.business.app.dtos.CreateUserParams;
import com.personal.todo.business.app.dtos.UpdateTodoParams;
import com.personal.todo.business.entities.Todo;
import com.personal.todo.business.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActions {
    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserParams params) {
        User user = new User(params.name(), params.email(), params.password());
        return userRepository.save(user);
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
