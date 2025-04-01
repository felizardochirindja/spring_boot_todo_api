package com.personal.taskie.business.entities;

import com.personal.taskie.business.types.TodoStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor
public final class Todo {
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
