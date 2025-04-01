package com.personal.taskie.adapters.repos;

import com.personal.taskie.business.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
