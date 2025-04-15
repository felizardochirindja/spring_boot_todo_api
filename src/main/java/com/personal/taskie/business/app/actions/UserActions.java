package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.entities.exceptions.EntityNotFoundException;
import com.personal.taskie.business.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserActions {
    @Autowired
    private UserRepository userRepository;

    public User readById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("user not found");
                });
    }
}
