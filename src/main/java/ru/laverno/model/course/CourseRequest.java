package ru.laverno.model.course;

import java.time.LocalDate;
import java.util.UUID;

public record CourseRequest(String name, LocalDate startDate, LocalDate endDate, UUID disciplineId) {
}
