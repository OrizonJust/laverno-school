package ru.laverno.model.homework;

import java.util.UUID;

public record HomeworkResponse(UUID studentId,
                               String studentName,
                               UUID practiceId,
                               String practiceName,
                               String practiceDescription,
                               int orderNum, UUID discipline,
                               String disciplineName,
                               UUID watcherId,
                               String watcherName,
                               boolean done) {
}
