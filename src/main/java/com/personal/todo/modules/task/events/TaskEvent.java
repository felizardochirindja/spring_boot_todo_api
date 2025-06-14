package com.personal.todo.modules.task.events;

import com.personal.todo.modules.events.entities.DomainEvent;
import com.personal.todo.modules.task.business.entities.Task;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public final class TaskEvent extends DomainEvent {
    private final Integer taskId;
    private final String title;
    private LocalDateTime timestamp;

    private TaskEvent(String name, Integer taskId, String title, LocalDateTime timestamp) {
        super(name);
        this.taskId = taskId;
        this.title = title;
        this.timestamp = timestamp;
    }

    public static TaskEvent fromTodo(Task task, TaskEventName eventName) {
        return new TaskEvent(eventName.name(), task.getId(), task.getTitle(), LocalDateTime.now());
    }
}
