package ru.laverno.model;

import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record EmbeddedStudentPracticeId(@Column(value = "s_id") UUID studentId, @Column(value = "p_id") UUID practiceId) {
}
