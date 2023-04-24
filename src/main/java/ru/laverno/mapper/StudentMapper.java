package ru.laverno.mapper;

import ru.laverno.model.course.CourseResponse;
import ru.laverno.model.student.StudentResponse;
import ru.laverno.model.user.UserResponse;

public class StudentMapper {

    private StudentMapper() {}

    public static StudentResponse studentToStudentResponse(UserResponse user, CourseResponse course, boolean disable) {
        return new StudentResponse(user.id(), user.username(), user.email(), course.id(), course.courseName(), course.disciplineName(), course.startTime(), disable);
    }
}
