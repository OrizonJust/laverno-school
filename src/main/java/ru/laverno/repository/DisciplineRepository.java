package ru.laverno.repository;

import org.springframework.data.repository.CrudRepository;
import ru.laverno.entitiy.Discipline;

import java.util.Optional;
import java.util.UUID;

public interface DisciplineRepository extends CrudRepository<Discipline, UUID> {

    Optional<Discipline> findDisciplineByName(String name);
}
