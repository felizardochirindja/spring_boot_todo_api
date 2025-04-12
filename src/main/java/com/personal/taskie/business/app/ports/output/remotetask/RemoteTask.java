package com.personal.taskie.business.app.ports.output.remotetask;

public record RemoteTask(
        Integer id,
        String todo,
        Boolean completed
) {
}
