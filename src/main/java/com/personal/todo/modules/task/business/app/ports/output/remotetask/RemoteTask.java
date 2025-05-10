package com.personal.todo.modules.task.business.app.ports.output.remotetask;

public record RemoteTask(
        Integer id,
        String todo,
        Boolean completed
) {
}
