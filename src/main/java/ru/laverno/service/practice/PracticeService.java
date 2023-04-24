package ru.laverno.service.practice;

import ru.laverno.model.practice.PracticeRequest;
import ru.laverno.model.practice.PracticeResponse;

import java.util.List;
import java.util.UUID;

public interface PracticeService {

    PracticeResponse getPracticeById(UUID id);

    List<PracticeResponse> getPracticesByDisciplineId(UUID disciplineId);

    List<PracticeResponse> getPracticesByName(String name);

    List<PracticeResponse> getAllPractices();

    List<PracticeResponse> getActivePractices();

    List<PracticeResponse> getDisablePractices();

    PracticeResponse addNewPractice(PracticeRequest course);

    PracticeResponse deletePractice(UUID id);
}
