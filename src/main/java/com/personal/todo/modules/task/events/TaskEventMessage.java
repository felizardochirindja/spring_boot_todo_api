package com.personal.todo.modules.task.events;

import com.personal.todo.modules.events.entities.EventMessage;
import com.personal.todo.modules.task.business.entities.Task;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public final class TaskEventMessage extends EventMessage {
    private final Integer taskId;
    private final String title;

    private TaskEventMessage(String name, Integer taskId, String title, LocalDateTime timestamp) {
        super(name, timestamp);
        this.taskId = taskId;
        this.title = title;
    }

    public static TaskEventMessage fromTodo(Task task, TaskEventName eventName) {
        return new TaskEventMessage(eventName.name(), task.getId(), task.getTitle(), LocalDateTime.now());
    }
}
