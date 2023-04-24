package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.practice.PracticeRequest;
import ru.laverno.model.practice.PracticeResponse;
import ru.laverno.service.practice.PracticeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {

    private final PracticeService practiceService;

    public PracticeController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

    @GetMapping("/{id}")
    public BasicResponse<PracticeResponse> getPracticeById(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<PracticeResponse>();
        final var uuid = UUID.fromString(id);
        response.setData(practiceService.getPracticeById(uuid));
        return response;
    }

    @GetMapping("/")
    public BasicResponse<List<PracticeResponse>> getAllPractices() {
        final var response = new BasicResponse<List<PracticeResponse>>();
        response.setData(practiceService.getAllPractices());
        return response;
    }

    @GetMapping("/discipline/{id}")
    public BasicResponse<List<PracticeResponse>> getPracticesByDisciplineId(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<List<PracticeResponse>>();
        final var uuid = UUID.fromString(id);
        response.setData(practiceService.getPracticesByDisciplineId(uuid));
        return response;
    }

    @GetMapping("/active")
    public BasicResponse<List<PracticeResponse>> getActivePractices() {
        final var response = new BasicResponse<List<PracticeResponse>>();
        response.setData(practiceService.getActivePractices());
        return response;
    }

    @GetMapping("/disable")
    public BasicResponse<List<PracticeResponse>> getDisablePractices() {
        final var response = new BasicResponse<List<PracticeResponse>>();
        response.setData(practiceService.getDisablePractices());
        return response;
    }

    @GetMapping("/name/{name}")
    public BasicResponse<List<PracticeResponse>> getPracticesByName(@PathVariable(name = "name") final String name) {
        final var response = new BasicResponse<List<PracticeResponse>>();
        response.setData(practiceService.getPracticesByName(name));
        return response;
    }

    @PostMapping("/")
    public BasicResponse<PracticeResponse> addNewCourse(@RequestBody PracticeRequest practice) {
        final var response = new BasicResponse<PracticeResponse>();
        response.setData(practiceService.addNewPractice(practice));
        return response;
    }

    @DeleteMapping("/{id}")
    public BasicResponse<PracticeResponse> deleteCourse(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<PracticeResponse>();
        response.setData(practiceService.deletePractice(UUID.fromString(id)));
        return response;
    }
}
