package ru.laverno.service.course;

import ru.laverno.model.course.CourseRequest;
import ru.laverno.model.course.CourseResponse;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseResponse getCourseById(UUID id);

    CourseResponse getCourseByName(String name);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getActiveCourses();

    List<CourseResponse> getDisableCourses();

    CourseResponse addNewCourse(CourseRequest course);

    CourseResponse deleteCourse(UUID id);
}
