package ru.laverno.service.course;

import ru.laverno.model.course.CourseRequest;
import ru.laverno.model.course.CourseResponse;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    //TODO: Создать поиск курсов по айди дисциплины

    CourseResponse getCourseById(UUID id);

    CourseResponse getCourseByName(String name);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getActiveCourses();

    List<CourseResponse> getDisableCourses();

    CourseResponse addNewCourse(CourseRequest course);

    CourseResponse deleteCourse(UUID id);
}
