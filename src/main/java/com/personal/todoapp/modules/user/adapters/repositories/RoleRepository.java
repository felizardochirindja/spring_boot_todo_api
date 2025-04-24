package com.personal.todoapp.modules.user.adapters.repositories;

import com.personal.todoapp.modules.user.business.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
