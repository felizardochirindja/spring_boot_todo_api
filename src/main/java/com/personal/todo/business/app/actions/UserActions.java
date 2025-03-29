package com.personal.todo.business.app.actions;

import com.personal.todo.adapters.repo.UserRepository;
import com.personal.todo.business.app.exceptions.EntityNotFoundException;
import com.personal.todo.business.app.params.CreateUserParams;
import com.personal.todo.business.app.params.UpdateTodoParams;
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

    public User readById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    public List<Todo> readAll() {
        return null;
    }

    public Todo update(UpdateTodoParams params) {
        return null;
    }
}
