package com.personal.task.business.app.ports.output.remotetask;

public record RemoteTask(
        Integer id,
        String todo,
        Boolean completed
) {
}
