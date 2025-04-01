package com.personal.todo.business.app.ports.output;

import com.personal.todo.business.entities.User;

public interface TokenGenerator {
    String generateToken(User user);
    String validateToken(String token);
}
