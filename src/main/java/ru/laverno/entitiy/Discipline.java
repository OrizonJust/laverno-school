package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(schema = "school", name = "sc_t_discipline")
public class Discipline {

    @Id
    @Column(value = "d_id")
    private final UUID id;

    @Column(value = "d_name")
    private final String name;

    public Discipline(String name) {
        this(null, name);
    }

    @PersistenceCreator
    public Discipline(UUID id, String name) {
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
