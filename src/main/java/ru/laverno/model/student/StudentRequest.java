package ru.laverno.model.student;

import java.util.UUID;

public record StudentRequest(UUID userId, UUID courseId) {
}
