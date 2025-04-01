package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class AuthActions {
    @Autowired
    private UserRepository userRepository;
}
