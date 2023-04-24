package ru.laverno.model.practice;

import java.util.UUID;

public record PracticeRequest(String name, String description, int orderNum, UUID disciplineId) {
}
