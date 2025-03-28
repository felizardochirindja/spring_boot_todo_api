package com.personal.todo.adapters.repo;

import com.personal.todo.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
