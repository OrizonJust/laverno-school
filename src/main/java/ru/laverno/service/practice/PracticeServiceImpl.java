package ru.laverno.service.practice;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Practice;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.mapper.PracticeMapper;
import ru.laverno.model.practice.PracticeRequest;
import ru.laverno.model.practice.PracticeResponse;
import ru.laverno.repository.PracticeRepository;
import ru.laverno.service.discipline.DisciplineService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PracticeServiceImpl implements PracticeService {

    private final PracticeRepository practiceRepository;

    private final DisciplineService disciplineService;

    public PracticeServiceImpl(PracticeRepository practiceRepository, DisciplineService disciplineService) {
        this.practiceRepository = practiceRepository;
        this.disciplineService = disciplineService;
    }

    @Override
    public PracticeResponse getPracticeById(UUID id) {
        final var practice = practiceRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Задание не найдено!"));
        final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
        return PracticeMapper.practiceToPracticeResponse(practice, discipline);
    }

    @Override
    public List<PracticeResponse> getPracticesByDisciplineId(UUID disciplineId) {
        final var practices = new ArrayList<PracticeResponse>();
        practiceRepository.findPracticesByDisciplineId(disciplineId).forEach(practice -> {
            final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
            practices.add(PracticeMapper.practiceToPracticeResponse(practice, discipline));
        });
        return practices;
    }

    @Override
    public List<PracticeResponse> getPracticesByName(String name) {
        final var practices = new ArrayList<PracticeResponse>();
        practiceRepository.findPracticesByName(name).forEach(practice -> {
            final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
            practices.add(PracticeMapper.practiceToPracticeResponse(practice, discipline));
        });
        return practices;
    }

    @Override
    public List<PracticeResponse> getAllPractices() {
        final var practices = new ArrayList<PracticeResponse>();
        practiceRepository.findAll().forEach(practice -> {
            final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
            practices.add(PracticeMapper.practiceToPracticeResponse(practice, discipline));
        });
        return practices;
    }

    @Override
    public List<PracticeResponse> getActivePractices() {
        final var practices = new ArrayList<PracticeResponse>();
        practiceRepository.findPracticesByDisable(false).forEach(practice -> {
            final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
            practices.add(PracticeMapper.practiceToPracticeResponse(practice, discipline));
        });
        return practices;
    }

    @Override
    public List<PracticeResponse> getDisablePractices() {
        final var practices = new ArrayList<PracticeResponse>();
        practiceRepository.findPracticesByDisable(true).forEach(practice -> {
            final var discipline = disciplineService.getDisciplineById(practice.getDisciplineId());
            practices.add(PracticeMapper.practiceToPracticeResponse(practice, discipline));
        });
        return practices;
    }

    @Override
    public PracticeResponse addNewPractice(PracticeRequest practice) {
        //TODO: при добавлении практики надо проверить есть ли в базе активное задание с таким же порядковым номером, если разница между последним порядковым номером и заданным значением > 1, то также выбивать ошибку.
        final var newPractice = practiceRepository.save(new Practice(practice.name(), practice.description(), practice.orderNum(), false, practice.disciplineId()));
        final var discipline = disciplineService.getDisciplineById(newPractice.getDisciplineId());
        return PracticeMapper.practiceToPracticeResponse(newPractice, discipline);
    }

    @Override
    public PracticeResponse deletePractice(UUID id) {
        final var practice = getPracticeById(id);
        final var deletedPractice = practiceRepository.save(new Practice(practice.id(), practice.name(), practice.description(), practice.orderNum(), true, practice.disciplineId()));
        final var discipline = disciplineService.getDisciplineById(deletedPractice.getDisciplineId());
        return PracticeMapper.practiceToPracticeResponse(deletedPractice, discipline);
    }
}
