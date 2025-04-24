package com.personal.todoapp.modules.user.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
public final class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    public Role(Values name, String description) {
        this.name = name.name();
        this.description = description;
    }

    public enum Values {
        ADMIN(1),
        USER(2);

        public final Integer id;

        Values(Integer id) {
            this.id = id;
        }
    }
}
