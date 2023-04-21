package ru.laverno.service.student;

import ru.laverno.entitiy.Student;
import ru.laverno.model.student.StudentRequest;
import ru.laverno.model.student.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<Student> getAllStudents();

    List<StudentResponse> getStudentsByStudentId(UUID studentId);

    List<StudentResponse> getStudentsByCourseId(UUID courseId);

    StudentResponse addNewStudent(StudentRequest student);
}
