package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table(schema = "school", name = "sc_t_student")
public class Student {

    @Id
    @Embedded.Empty
    private final EmbeddedStudentId id;

    @Column(value = "s_start_date")
    private final LocalDate startDate;

    @PersistenceCreator
    public Student(EmbeddedStudentId id, LocalDate startDate) {
        this.id = id;
        this.startDate = startDate;
    }

    public EmbeddedStudentId getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static class EmbeddedStudentId {
        @Column(value = "usr_id")
        private final UUID userId;

        @Column(value = "c_id")
        private final UUID courseId;

        public EmbeddedStudentId(UUID userId, UUID courseId) {
            this.userId = userId;
            this.courseId = courseId;
        }

        public UUID getUserId() {
            return userId;
        }

        public UUID getCourseId() {
            return courseId;
        }
    }
}
