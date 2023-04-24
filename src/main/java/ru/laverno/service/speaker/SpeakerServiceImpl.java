package ru.laverno.service.speaker;

import org.springframework.stereotype.Service;
import ru.laverno.mapper.SpeakerMapper;
import ru.laverno.model.speaker.SpeakerRequest;
import ru.laverno.model.speaker.SpeakerResponse;
import ru.laverno.repository.SpeakerRepository;
import ru.laverno.service.course.CourseService;
import ru.laverno.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    private final SpeakerRepository speakerRepository;

    private final UserService userService;

    private final CourseService courseService;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository, UserService userService, CourseService courseService) {
        this.speakerRepository = speakerRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public List<SpeakerResponse> getAllSpeakers() {
        final var speakers = new ArrayList<SpeakerResponse>();
        speakerRepository.findAll().forEach(student -> {
            final var user = userService.getUserById(student.getId().userId());
            final var course = courseService.getCourseById(student.getId().courseId());
            speakers.add(SpeakerMapper.speakerToSpeakerResponse(user, course, student.getDisable()));
        });
        return speakers;
    }

    @Override
    public List<SpeakerResponse> getSpeakersBySpeakerId(UUID speakerId) {
        final var speakers = new ArrayList<SpeakerResponse>();
        speakerRepository.getSpeakersByUserId(speakerId).forEach(student -> {
            final var user = userService.getUserById(student.getId().userId());
            final var course = courseService.getCourseById(student.getId().courseId());
            speakers.add(SpeakerMapper.speakerToSpeakerResponse(user, course, student.getDisable()));
        });
        return speakers;
    }

    @Override
    public List<SpeakerResponse> getSpeakersByCourseId(UUID courseId) {
        final var speakers = new ArrayList<SpeakerResponse>();
        speakerRepository.getSpeakersByCourseId(courseId).forEach(student -> {
            final var user = userService.getUserById(student.getId().userId());
            final var course = courseService.getCourseById(student.getId().courseId());
            speakers.add(SpeakerMapper.speakerToSpeakerResponse(user, course, student.getDisable()));
        });
        return speakers;
    }

    @Override
    public SpeakerResponse addNewSpeaker(SpeakerRequest speaker) {
        final var user = userService.getUserById(speaker.userId());
        final var course = courseService.getCourseById(speaker.courseId());
        speakerRepository.insertSpeaker(user.id(), course.id(), course.startTime());

        return SpeakerMapper.speakerToSpeakerResponse(user, course, false);
    }

    @Override
    public SpeakerResponse deleteSpeaker(SpeakerRequest speaker) {
        final var user = userService.getUserById(speaker.userId());
        final var course = courseService.getCourseById(speaker.courseId());
        speakerRepository.deleteSpeakerById(speaker.userId(), speaker.courseId());

        return SpeakerMapper.speakerToSpeakerResponse(user, course, true);
    }
}
