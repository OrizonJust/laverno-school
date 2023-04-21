package ru.laverno.service.discipline;

import ru.laverno.entitiy.Discipline;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.exception.ParamValidationException;
import ru.laverno.model.discipline.DisciplineRequest;

import java.util.List;
import java.util.UUID;

public interface DisciplineService {

    Discipline getDisciplineById(UUID id) throws DataNotFoundException;

    Discipline getDisciplineByName(String name) throws ParamValidationException, DataNotFoundException;

    List<Discipline> getDisciplines();

    Discipline addNewDiscipline(DisciplineRequest discipline);

    Discipline editDiscipline(DisciplineRequest discipline) throws ParamValidationException, DataNotFoundException, DataAlreadyExistsException;

    boolean deleteDiscipline(UUID id) throws DataNotFoundException;
}
