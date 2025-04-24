package com.personal.todoapp.modules.auth.business.app.actions.ports.output;

import com.personal.todoapp.modules.auth.business.entities.AuthUser;

public interface TokenGenerator {
    String generateToken(AuthUser user);
    String validateToken(String token);
}
