package ru.laverno.mapper;

import ru.laverno.model.course.CourseResponse;
import ru.laverno.model.speaker.SpeakerResponse;
import ru.laverno.model.user.UserResponse;

public class SpeakerMapper {

    private SpeakerMapper() {}

    public static SpeakerResponse speakerToSpeakerResponse(UserResponse user, CourseResponse course, boolean disable) {
        return new SpeakerResponse(user.id(), user.username(), user.email(), course.id(), course.courseName(), course.disciplineName(), course.startTime(), disable);
    }
}
