package com.personal.todo.business.entities;

import com.personal.todo.business.types.TodoStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;
    @ManyToOne
    private User user;

    public Todo(String title, TodoStatus status, User user) {
        this.title = title;
        this.status = status;
        this.user = user;
    }
}
