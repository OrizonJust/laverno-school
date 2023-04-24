package ru.laverno.model.homework;

import java.util.UUID;

public record HomeworkRequest(UUID studentId, UUID practiceId, UUID watcherId) {
}
