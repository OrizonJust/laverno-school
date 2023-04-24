package ru.laverno.mapper;

import ru.laverno.entitiy.Discipline;
import ru.laverno.entitiy.Practice;
import ru.laverno.model.practice.PracticeResponse;

public class PracticeMapper {

    private PracticeMapper() {}

    public static PracticeResponse practiceToPracticeResponse(Practice practice, Discipline discipline) {
        return new PracticeResponse(practice.getId(), practice.getName(), practice.getDescription(), practice.getOrderNum(), practice.isDisable(), discipline.getId(), discipline.getName());
    }
}
