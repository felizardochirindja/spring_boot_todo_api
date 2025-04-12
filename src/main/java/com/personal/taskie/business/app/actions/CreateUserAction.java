package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.input.CreateUserInput;
import com.personal.taskie.business.app.ports.input.UserCreator;
import com.personal.taskie.business.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class CreateUserAction implements UserCreator {
    @Autowired
    private UserRepository userRepository;

    public User execute(CreateUserInput params) {
        userRepository.findByEmail(params.email())
                .ifPresent(user -> {
                    throw new RuntimeException("user already exists!");
                });

        return userRepository.save(params.createUser());
    }
}
