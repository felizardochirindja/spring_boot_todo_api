package com.personal.todo.business.entities;

import com.personal.todo.business.types.TodoStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Todo {
    private String id;
    private String title;
    private TodoStatus status;
    private User user;
}
