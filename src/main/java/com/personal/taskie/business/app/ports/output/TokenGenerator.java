package com.personal.taskie.business.app.ports.output;

import com.personal.taskie.business.entities.AuthUser;

public interface TokenGenerator {
    String generateToken(AuthUser user);
    String validateToken(String token);
}
