package com.personal.taskie.adapters.repos;

import com.personal.taskie.business.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
