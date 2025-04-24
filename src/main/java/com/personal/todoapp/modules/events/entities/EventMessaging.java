package com.personal.todoapp.modules.events.entities;

import java.time.LocalDateTime;

public interface EventMessaging {
    String getName();
    LocalDateTime getTimestamp();
}
