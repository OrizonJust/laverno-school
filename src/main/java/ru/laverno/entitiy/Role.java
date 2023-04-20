package ru.laverno.entitiy;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(schema = "school", name = "sc_t_role")
public class Role {

    @Id
    @Column(value = "r_id")
    private final UUID id;

    @Nonnull
    @Column(value = "r_name")
    private final String name;

    public Role(String name) {
        this(null, name);
    }

    @PersistenceCreator
    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
