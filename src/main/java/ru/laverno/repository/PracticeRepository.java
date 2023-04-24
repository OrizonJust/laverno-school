package ru.laverno.repository;

import org.springframework.data.repository.CrudRepository;
import ru.laverno.entitiy.Practice;

import java.util.UUID;

public interface PracticeRepository extends CrudRepository<Practice, UUID> {

    Iterable<Practice> findPracticesByDisciplineId(UUID disciplineId);

    Iterable<Practice> findPracticesByName(String name);

    Iterable<Practice> findPracticesByDisable(boolean disable);
}
