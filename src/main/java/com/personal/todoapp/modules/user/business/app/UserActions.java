package com.personal.todoapp.modules.user.business.app;

import com.personal.todoapp.modules.user.adapters.repositories.UserRepository;
import com.personal.todoapp.modules.shared.exceptions.EntityNotFoundException;
import com.personal.todoapp.modules.user.business.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserActions {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserActions.class);

    public User readById(int id) {
        logger.atInfo()
                .setMessage("reading user!")
                .addKeyValue("userId", id)
                .log();

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.atWarn()
                            .setMessage("User not found!")
                            .addKeyValue("userId", id)
                            .log();

                    return new EntityNotFoundException("user not found");
                });

        logger.atInfo()
                .setMessage("user read successfully!")
                .addKeyValue("userId", user.getId())
                .addKeyValue("email", user.getEmail())
                .log();

        return user;
    }
}
