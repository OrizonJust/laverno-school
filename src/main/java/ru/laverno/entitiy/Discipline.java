package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table(schema = "school", name = "sc_t_discipline")
public class Discipline {

    @Id
    @Column(value = "d_id")
    private final UUID id;

    @Column(value = "d_name")
    private final String name;

    @MappedCollection(idColumn = "d_id")
    private final Set<Course> course;

    public Discipline(String name, Set<Course> course) {
        this(null, name, course);
    }

    @PersistenceCreator
    public Discipline(UUID id, String name, Set<Course> course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourse() {
        return course;
    }
}
