package ru.laverno.mapper;

import ru.laverno.entitiy.Course;
import ru.laverno.entitiy.Discipline;
import ru.laverno.model.course.CourseResponse;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseResponse courseToCourseResponse(Course course, Discipline discipline) {
        return new CourseResponse(course.getId(), course.getName(), course.getStartTs(), course.getEndTs(), course.getDisable(), discipline.getId(), discipline.getName());
    }
}
