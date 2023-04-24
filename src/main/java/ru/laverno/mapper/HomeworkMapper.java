package ru.laverno.mapper;

import ru.laverno.entitiy.*;
import ru.laverno.model.EmbeddedStudentPracticeId;
import ru.laverno.model.homework.HomeworkRequest;
import ru.laverno.model.homework.HomeworkResponse;
import ru.laverno.model.practice.PracticeResponse;
import ru.laverno.model.user.UserResponse;

public class HomeworkMapper {

    private HomeworkMapper() {}

    public static HomeworkResponse homeworkToHomeworkResponse(Homework homework, UserResponse student, UserResponse watcher, PracticeResponse practice, Discipline discipline) {
        return new HomeworkResponse(student.id(),
                student.username(),
                practice.id(),
                practice.name(),
                practice.description(),
                practice.orderNum(),
                discipline.getId(),
                discipline.getName(),
                watcher != null ? watcher.id() : null,
                watcher != null ? watcher.username() : null,
                homework.isDone());
    }

    public static Homework homewrokRequestToHomework(HomeworkRequest homework, boolean done) {
        return new Homework(new EmbeddedStudentPracticeId(homework.studentId(), homework.practiceId()), homework.watcherId(), done);

    }
}
