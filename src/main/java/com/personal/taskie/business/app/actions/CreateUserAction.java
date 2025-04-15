package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.input.CreateUserInput;
import com.personal.taskie.business.app.ports.input.UserCreator;
import com.personal.taskie.business.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class CreateUserAction implements UserCreator {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CreateUserAction.class);

    public User execute(CreateUserInput params) {
        logger.atInfo()
                .setMessage("Trying to create user!")
                .addKeyValue("email", params.email())
                .log();

        userRepository.findByEmail(params.email()).ifPresent(user -> {
            logger.atWarn()
                    .setMessage("User already exists")
                    .addKeyValue("email", params.email())
                    .log();

            throw new RuntimeException("User already exists!");
        });

        User createdUser = userRepository.save(params.createUser());

        logger.atInfo()
                .setMessage("User created successfully!")
                .addKeyValue("userId", createdUser.getId())
                .addKeyValue("email", createdUser.getEmail())
                .log();

        return createdUser;
    }
}
