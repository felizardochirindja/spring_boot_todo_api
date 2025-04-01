package com.personal.taskie.business.app.ports.output;

import com.personal.taskie.business.entities.User;

public interface TokenGenerator {
    String generateToken(User user);
    String validateToken(String token);
}
