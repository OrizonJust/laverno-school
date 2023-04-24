package ru.laverno.model;

import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record EmbeddedUserCourseId(@Column(value = "usr_id")UUID userId, @Column(value = "c_id") UUID courseId) {
}
