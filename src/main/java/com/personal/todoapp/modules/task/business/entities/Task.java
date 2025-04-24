package com.personal.todoapp.modules.task.business.entities;

import com.personal.todoapp.modules.task.business.types.TaskStatus;
import com.personal.todoapp.modules.user.business.entities.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@NoArgsConstructor
public final class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    private User user;

    public Task(String title, User user) {
        this.title = title;
        this.status = TaskStatus.PENDING;
        this.user = user;
    }

    public Task(Integer id, String title, TaskStatus status, User user) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.user = user;
    }

    public void complete() {
        if (status == TaskStatus.COMPLETED) {
            return;
        }

        status = TaskStatus.COMPLETED;
    }
}
