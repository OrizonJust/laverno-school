package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import ru.laverno.model.EmbeddedUserCourseId;

import java.time.LocalDate;

@Table(schema = "school", name = "sc_t_speaker")
public class Speaker {

    @Id
    @Embedded.Empty
    private final EmbeddedUserCourseId id;

    @Column(value = "sp_start_date")
    private final LocalDate startDate;

    @Column(value = "sp_disable")
    private final Boolean disable;

    @PersistenceCreator
    public Speaker(EmbeddedUserCourseId id, LocalDate startDate, Boolean disable) {
        this.id = id;
        this.startDate = startDate;
        this.disable = disable;
    }

    public EmbeddedUserCourseId getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Boolean getDisable() {
        return disable;
    }
}
