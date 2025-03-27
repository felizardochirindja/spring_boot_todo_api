package com.personal.todo.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
}
