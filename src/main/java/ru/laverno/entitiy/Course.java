package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table(schema = "school", name = "sc_t_course")
public class Course {

    @Id
    @Column(value = "c_id")
    private final UUID id;

    @Column(value = "c_name")
    private final String name;

    @Column(value = "c_start_ts")
    private final LocalDate startTs;

    @Column(value = "c_end_ts")
    private final LocalDate endTs;

    @Column(value = "c_disable")
    private final Boolean disable;

    @Column(value = "d_id")
    private final UUID disciplineId;

    public Course(String name, LocalDate startTs, LocalDate endTs, Boolean disable, UUID disciplineId) {
        this(null, name, startTs, endTs, disable, disciplineId);
    }

    @PersistenceCreator
    public Course(UUID id, String name, LocalDate startTs, LocalDate endTs, Boolean disable, UUID disciplineId) {
        this.id = id;
        this.name = name;
        this.startTs = startTs;
        this.endTs = endTs;
        this.disable = disable;
        this.disciplineId = disciplineId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartTs() {
        return startTs;
    }

    public LocalDate getEndTs() {
        return endTs;
    }

    public Boolean getDisable() {
        return disable;
    }

    public UUID getDisciplineId() {
        return disciplineId;
    }
}
