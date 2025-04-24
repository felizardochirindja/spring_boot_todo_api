package com.personal.todoapp.modules.user.business.app;

import com.personal.todoapp.modules.user.adapters.repositories.RoleRepository;
import com.personal.todoapp.modules.user.adapters.repositories.UserRepository;
import com.personal.todoapp.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todoapp.modules.user.business.app.ports.input.UserCreator;
import com.personal.todoapp.modules.user.business.entities.Role;
import com.personal.todoapp.modules.user.business.entities.User;
import com.personal.todoapp.modules.shared.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserAction implements UserCreator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(CreateUserAction.class);

    @Transactional
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

            throw new RuntimeException("user already exists!");
        });

        Role role = roleRepository.findById(params.role().id)
                .orElseThrow(() -> {
                    logger.atError()
                            .setMessage("role doesnt exists")
                            .addKeyValue("id", params.role().id)
                            .log();

                    return new EntityNotFoundException("role not found!");
                });

        User createdUser = userRepository.save(params.createUser(role));

        logger.atInfo()
                .setMessage("User created successfully!")
                .addKeyValue("userId", createdUser.getId())
                .addKeyValue("email", createdUser.getEmail())
                .log();

        return createdUser;
    }
}
