package ru.laverno.service.homework;

import ru.laverno.model.homework.HomeworkRequest;
import ru.laverno.model.homework.HomeworkResponse;

import java.util.List;
import java.util.UUID;

public interface HomeworkService {

    List<HomeworkResponse> getHomeworkByStudentId(UUID id);

    List<HomeworkResponse> getHomeworkByPracticeId(UUID id);

    HomeworkResponse addHomework(HomeworkRequest homework);

    HomeworkResponse addWatcherIntoHomework(HomeworkRequest watcherId);

    HomeworkResponse doneHomework(HomeworkRequest homework);
}
