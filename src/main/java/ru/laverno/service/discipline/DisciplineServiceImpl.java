package ru.laverno.service.discipline;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Discipline;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.exception.ParamValidationException;
import ru.laverno.mapper.DisciplineMapper;
import ru.laverno.model.discipline.DisciplineRequest;
import ru.laverno.repository.DisciplineRepository;
import ru.laverno.utils.Const;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public Discipline getDisciplineById(UUID id) throws DataNotFoundException {
        return disciplineRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_ID_EXCEPTION, Const.DISCIPLINE, id)));
    }

    @Override
    public Discipline getDisciplineByName(String name) throws ParamValidationException, DataNotFoundException {
        if (name == null) {
            throw new ParamValidationException("Валидация параметра [name=null] не прошла!");
        }
        return disciplineRepository.findDisciplineByName(name).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_NAME_EXCEPTION, Const.DISCIPLINE, name)));
    }

    @Override
    public List<Discipline> getDisciplines() {
        final var disciplines = new ArrayList<Discipline>();
        disciplineRepository.findAll().forEach(disciplines::add);

        if (disciplines.isEmpty()) {
            throw new DataNotFoundException(String.format(Const.NOT_FOUND_ANY_EXCEPTION, Const.DISCIPLINE));
        }
        return disciplines;
    }

    @Override
    public Discipline addNewDiscipline(DisciplineRequest discipline) {
        if (discipline.name() == null) {
            throw new ParamValidationException("Невозможно создать новую дисциплину, из-за имени [name=null]!");
        }

        try {
            getDisciplineByName(discipline.name());
            throw new DataAlreadyExistsException(String.format("Дисциплина с именем [name=%s] уже существует!", discipline.name()));
        } catch (DataNotFoundException ex) {
            return disciplineRepository.save(new Discipline(discipline.name(), new HashSet<>()));
        }
    }

    @Override
    public Discipline editDiscipline(DisciplineRequest discipline) throws ParamValidationException, DataNotFoundException, DataAlreadyExistsException {
        if (discipline.id() == null) {
            throw new ParamValidationException("Идентификатор дисциплины [id=null] равен NULL!");
        }
        if (discipline.name() == null || discipline.name().equals("")) {
            throw new ParamValidationException(String.format("Наименование дисциплины [name=%s] не прошло валидацию!", discipline.name()));
        }

        final var disciplineFromDb = disciplineRepository.findById(discipline.id()).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_ID_EXCEPTION, Const.DISCIPLINE, discipline.id())));
        if (disciplineRepository.findDisciplineByName(discipline.name()).isPresent()) {
            throw new DataAlreadyExistsException(String.format("Дисциплина с именем [name=%s] уже существует!", discipline.name()));
        }

        return disciplineRepository.save(DisciplineMapper.disciplineRequestToEditedDiscipline(disciplineFromDb, discipline));
    }

    @Override
    public boolean deleteDiscipline(UUID id) throws DataNotFoundException {
        if (id == null) {
            throw new ParamValidationException("Идентификатор дисциплины [id=null] равен NULL!");
        }

        final var discipline = disciplineRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_ID_EXCEPTION, Const.DISCIPLINE, id)));

        disciplineRepository.delete(discipline);
        return true;
    }
}
