package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import ru.laverno.model.EmbeddedStudentPracticeId;

import java.util.UUID;

@Table(schema = "school", name = "sc_t_homework")
public class Homework {

    @Id
    @Embedded.Empty
    private final EmbeddedStudentPracticeId id;

    @Column(value = "usr_id")
    private final UUID watcherId;

    @Column(value = "h_done")
    private final boolean done;

    @PersistenceCreator
    public Homework(EmbeddedStudentPracticeId id, UUID watcherId, boolean done) {
        this.id = id;
        this.watcherId = watcherId;
        this.done = done;
    }

    public EmbeddedStudentPracticeId getId() {
        return id;
    }

    public UUID getWatcherId() {
        return watcherId;
    }

    public boolean isDone() {
        return done;
    }
}
