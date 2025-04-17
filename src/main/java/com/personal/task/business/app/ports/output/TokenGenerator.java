package com.personal.task.business.app.ports.output;

import com.personal.task.business.entities.AuthUser;

public interface TokenGenerator {
    String generateToken(AuthUser user);
    String validateToken(String token);
}
