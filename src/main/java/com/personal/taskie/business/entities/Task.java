package com.personal.taskie.business.entities;

import com.personal.taskie.business.types.TodoStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor
public final class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;
    @ManyToOne
    private User user;

    public Task(String title, User user) {
        this.title = title;
        this.status = TodoStatus.PENDING;
        this.user = user;
    }

    public Task(Integer id, String title, TodoStatus status, User user) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.user = user;
    }
}
