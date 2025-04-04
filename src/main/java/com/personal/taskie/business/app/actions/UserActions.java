package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateUserParams;
import com.personal.taskie.business.app.params.SignupParams;
import com.personal.taskie.business.app.params.UpdateTaskParams;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserActions {
    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserParams params) {
        userRepository.findByEmail(params.email())
                        .orElseThrow(() -> new RuntimeException("user not found!"));

        User user = params.createUser();
        return userRepository.save(user);
    }

    public User readById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    public List<Task> readAll() {
        return null;
    }

    public Task update(UpdateTaskParams params) {
        return null;
    }
}
