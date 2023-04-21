package ru.laverno.model.student;

import java.time.LocalDate;
import java.util.UUID;

public record StudentResponse(UUID userId, String username, String email, UUID courseId, String courseName, String disciplineName, LocalDate startDate) {
}
