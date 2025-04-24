package com.personal.todoapp.modules.task.adapters.repositories;

import com.personal.todoapp.modules.task.business.entities.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByUserId(Integer userId);
}
