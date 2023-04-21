package ru.laverno.model.course;

import java.time.LocalDate;
import java.util.UUID;

public record CourseResponse(UUID id, String courseName, LocalDate startTime, LocalDate endTime, boolean disable, UUID disciplineId, String disciplineName) {
}
