package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.homework.HomeworkRequest;
import ru.laverno.model.homework.HomeworkResponse;
import ru.laverno.service.homework.HomeworkService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/homework")
public class HomeworkController {

    //TODO: добавить метод получения домашних заданий по связи student-course

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/student/{id}")
    public BasicResponse<List<HomeworkResponse>> getHomeworkByStudentId(@PathVariable(name = "id") final UUID id) {
        final var response = new BasicResponse<List<HomeworkResponse>>();
        response.setData(homeworkService.getHomeworkByStudentId(id));
        return response;
    }

    @GetMapping("/practice/{id}")
    public BasicResponse<List<HomeworkResponse>> getHomeworkByPracticeId(@PathVariable(name = "id") final UUID id) {
        final var response = new BasicResponse<List<HomeworkResponse>>();
        response.setData(homeworkService.getHomeworkByPracticeId(id));
        return response;
    }

    @PostMapping("/")
    public BasicResponse<HomeworkResponse> addNewHomework(@RequestBody final HomeworkRequest homeworkRequest) {
        final var response = new BasicResponse<HomeworkResponse>();
        response.setData(homeworkService.addHomework(homeworkRequest));
        return response;
    }

    @PutMapping("/watcher")
    public BasicResponse<HomeworkResponse> addHomeworkWatcher(@RequestBody final HomeworkRequest homeworkRequest) {
        final var response = new BasicResponse<HomeworkResponse>();
        response.setData(homeworkService.addWatcherIntoHomework(homeworkRequest));
        return response;
    }

    @PutMapping("/done")
    public BasicResponse<HomeworkResponse> doneHomework(@RequestBody final HomeworkRequest homeworkRequest) {
        final var response = new BasicResponse<HomeworkResponse>();
        response.setData(homeworkService.doneHomework(homeworkRequest));
        return response;
    }
}
