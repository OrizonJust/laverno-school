package ru.laverno.service.speaker;

import ru.laverno.model.speaker.SpeakerRequest;
import ru.laverno.model.speaker.SpeakerResponse;

import java.util.List;
import java.util.UUID;

public interface SpeakerService {

    List<SpeakerResponse> getAllSpeakers();

    List<SpeakerResponse> getSpeakersBySpeakerId(UUID speakerId);

    List<SpeakerResponse> getSpeakersByCourseId(UUID courseId);

    SpeakerResponse addNewSpeaker(SpeakerRequest speaker);

    SpeakerResponse deleteSpeaker(SpeakerRequest speaker);
}
