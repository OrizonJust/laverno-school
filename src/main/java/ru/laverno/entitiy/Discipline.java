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

    @Column(value = "d_disable")
    private final Boolean disable;

    @MappedCollection(idColumn = "d_id")
    private final Set<Course> course;

    @MappedCollection(idColumn = "d_id")
    private final Set<Practice> practice;

    public Discipline(String name, Boolean disable, Set<Course> course, Set<Practice> practice) {
        this(null, name, disable, course, practice);
    }

    @PersistenceCreator
    public Discipline(UUID id, String name, Boolean disable, Set<Course> course, Set<Practice> practice) {
        this.id = id;
        this.name = name;
        this.disable = disable;
        this.course = course;
        this.practice = practice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getDisable() {
        return disable;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public Set<Practice> getPractice() {
        return practice;
    }
}
