package ru.laverno.service.homework;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Homework;
import ru.laverno.mapper.HomeworkMapper;
import ru.laverno.model.homework.HomeworkRequest;
import ru.laverno.model.homework.HomeworkResponse;
import ru.laverno.model.user.UserResponse;
import ru.laverno.repository.HomeworkRepository;
import ru.laverno.service.discipline.DisciplineService;
import ru.laverno.service.practice.PracticeService;
import ru.laverno.service.student.StudentService;
import ru.laverno.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    private final StudentService studentService;

    private final PracticeService practiceService;

    private final UserService userService;

    private final DisciplineService disciplineService;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, StudentService studentService, PracticeService practiceService, UserService userService, DisciplineService disciplineService) {
        this.homeworkRepository = homeworkRepository;
        this.studentService = studentService;
        this.practiceService = practiceService;
        this.userService = userService;
        this.disciplineService = disciplineService;
    }

    @Override
    public List<HomeworkResponse> getHomeworkByStudentId(UUID id) {
        final var homeworks = new ArrayList<HomeworkResponse>();
        homeworkRepository.findHomeworksByStudentId(id).forEach(homework -> homeworks.add(addSubPartsIntoHomeworkResponse(homework)));
        return homeworks;
    }

    @Override
    public List<HomeworkResponse> getHomeworkByPracticeId(UUID id) {
        final var homeworks = new ArrayList<HomeworkResponse>();
        homeworkRepository.findHomeworksByPracticeId(id).forEach(homework -> homeworks.add(addSubPartsIntoHomeworkResponse(homework)));
        return homeworks;
    }

    @Override
    public HomeworkResponse addHomework(HomeworkRequest homeworkRequest) {
        final var result = addSubPartsIntoHomeworkResponse(HomeworkMapper.homewrokRequestToHomework(homeworkRequest, false));
        homeworkRepository.insertHomework(homeworkRequest.studentId(), homeworkRequest.practiceId());
        return result;
    }

    @Override
    public HomeworkResponse addWatcherIntoHomework(HomeworkRequest homeworkRequest) {
        final var result = addSubPartsIntoHomeworkResponse(HomeworkMapper.homewrokRequestToHomework(homeworkRequest, false));
        homeworkRepository.updateHomeworkWatcherId(homeworkRequest.watcherId(), homeworkRequest.studentId(), homeworkRequest.practiceId());
        return result;
    }

    @Override
    public HomeworkResponse doneHomework(HomeworkRequest homeworkRequest) {
        final var result = addSubPartsIntoHomeworkResponse(HomeworkMapper.homewrokRequestToHomework(homeworkRequest, true));
        homeworkRepository.updateHomeworkDone(result.done(), result.studentId(), result.practiceId());
        return result;
    }

    private HomeworkResponse addSubPartsIntoHomeworkResponse(Homework homework) {
        final var student = studentService.getStudentsByStudentId(homework.getId().studentId());
        final var practice = practiceService.getPracticeById(homework.getId().practiceId());
        UserResponse watcherUser = null;
        if (homework.getWatcherId() != null) {
            watcherUser = userService.getUserById(homework.getWatcherId());
        }
        final var studentUser = userService.getUserById(student.get(0).userId());
        final var discipline = disciplineService.getDisciplineById(practice.disciplineId());

        return HomeworkMapper.homeworkToHomeworkResponse(homework, studentUser, watcherUser, practice, discipline);
    }

}
