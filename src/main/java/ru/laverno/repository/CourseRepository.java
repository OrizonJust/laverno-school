package ru.laverno.repository;

import org.springframework.data.repository.CrudRepository;
import ru.laverno.entitiy.Course;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends CrudRepository<Course, UUID> {

    Optional<Course> findCourseByName(String name);

    Iterable<Course> findCoursesByDisable(boolean disable);
}
