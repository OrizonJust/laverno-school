package ru.laverno.model.practice;

import java.util.UUID;

public record PracticeResponse(UUID id, String name, String description, int orderNum, boolean disable, UUID disciplineId, String disciplineName) {
}
