package ru.laverno.model.speaker;

import java.util.UUID;

public record SpeakerRequest(UUID userId, UUID courseId) {
}
