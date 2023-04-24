package ru.laverno.model.speaker;

import java.time.LocalDate;
import java.util.UUID;

public record SpeakerResponse(UUID userId, String username, String email, UUID courseId, String courseName, String disciplineName, LocalDate startDate, boolean disable) {
}
