package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import ru.laverno.model.EmbeddedUserCourseId;

import java.time.LocalDate;

@Table(schema = "school", name = "sc_t_student")
public class Student {

    @Id
    @Embedded.Empty
    private final EmbeddedUserCourseId id;

    @Column(value = "st_start_date")
    private final LocalDate startDate;

    @Column(value = "st_done")
    private final Boolean done;

    @Column(value = "st_disable")
    private final Boolean disable;

    @PersistenceCreator
    public Student(EmbeddedUserCourseId id, LocalDate startDate, Boolean done, Boolean disable) {
        this.id = id;
        this.startDate = startDate;
        this.done = done;
        this.disable = disable;
    }

    public EmbeddedUserCourseId getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Boolean getDone() {
        return done;
    }

    public Boolean getDisable() {
        return disable;
    }
}
