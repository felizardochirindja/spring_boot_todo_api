package com.personal.todo.modules.events.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor()
@Getter
public abstract class DomainEvent {
    private String name;
}
