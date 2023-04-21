package ru.laverno.mapper;

import ru.laverno.entitiy.Discipline;
import ru.laverno.model.discipline.DisciplineRequest;

public class DisciplineMapper {

    private DisciplineMapper() {}

    public static Discipline disciplineRequestToEditedDiscipline(Discipline discipline, DisciplineRequest disciplineRequest) {
        return new Discipline(discipline.getId(),
                disciplineRequest.name() != null ? disciplineRequest.name() : discipline.getName(),
                discipline.getCourse());
    }
}
