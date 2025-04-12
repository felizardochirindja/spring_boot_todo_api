package com.personal.taskie.infra.adapters.libs.dummyjson;

public record RemoteTask(
        Integer id,
        String todo,
        Boolean completed
) {
}
