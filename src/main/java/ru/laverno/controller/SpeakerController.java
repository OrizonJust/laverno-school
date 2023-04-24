package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.speaker.SpeakerRequest;
import ru.laverno.model.speaker.SpeakerResponse;
import ru.laverno.service.speaker.SpeakerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/speaker")
public class SpeakerController {

    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping("/")
    public BasicResponse<List<SpeakerResponse>> getAllSpeakers() {
        final var response = new BasicResponse<List<SpeakerResponse>>();
        response.setData(speakerService.getAllSpeakers());
        return response;
    }

    @GetMapping("/user/{speaker_id}")
    public BasicResponse<List<SpeakerResponse>> getSpeakersBySpeakerId(@PathVariable(name = "speaker_id") final String speakerId) {
        final var response = new BasicResponse<List<SpeakerResponse>>();
        response.setData(speakerService.getSpeakersBySpeakerId(UUID.fromString(speakerId)));
        return response;
    }

    @GetMapping("/course/{course_id}")
    public BasicResponse<List<SpeakerResponse>> getSpeakersByCourseId(@PathVariable(name = "course_id") final String courseId) {
        final var response = new BasicResponse<List<SpeakerResponse>>();
        response.setData(speakerService.getSpeakersByCourseId(UUID.fromString(courseId)));
        return response;
    }

    @PostMapping("/")
    public BasicResponse<SpeakerResponse> addNewSpeaker(@RequestBody SpeakerRequest speaker) {
        final var response = new BasicResponse<SpeakerResponse>();
        response.setData(speakerService.addNewSpeaker(speaker));
        return  response;
    }

    @DeleteMapping("/")
    public BasicResponse<SpeakerResponse> editSpeaker(@RequestBody SpeakerRequest speaker) {
        final var response = new BasicResponse<SpeakerResponse>();
        response.setData(speakerService.deleteSpeaker(speaker));
        return response;
    }
}
