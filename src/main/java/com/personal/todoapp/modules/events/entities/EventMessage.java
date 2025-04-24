package com.personal.todoapp.modules.events.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor()
public abstract class EventMessage implements EventMessaging {
    private String name;
    private LocalDateTime timestamp;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
