package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.student.StudentRequest;
import ru.laverno.model.student.StudentResponse;
import ru.laverno.service.student.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public BasicResponse<List<StudentResponse>> getAllStudents() {
        final var response = new BasicResponse<List<StudentResponse>>();
        response.setData(studentService.getAllStudents());
        return response;
    }

    @GetMapping("/user/{student_id}")
    public BasicResponse<List<StudentResponse>> getStudentsByStudentId(@PathVariable(name = "student_id") final String studentId) {
        final var response = new BasicResponse<List<StudentResponse>>();
        response.setData(studentService.getStudentsByStudentId(UUID.fromString(studentId)));
        return response;
    }

    @GetMapping("/course/{course_id}")
    public BasicResponse<List<StudentResponse>> getStudentsByCourseId(@PathVariable(name = "course_id") final String courseId) {
        final var response = new BasicResponse<List<StudentResponse>>();
        response.setData(studentService.getStudentsByCourseId(UUID.fromString(courseId)));
        return response;
    }

    @PostMapping("/")
    public BasicResponse<StudentResponse> addNewStudent(@RequestBody StudentRequest student) {
        final var response = new BasicResponse<StudentResponse>();
        response.setData(studentService.addNewStudent(student));
        return  response;
    }

    @DeleteMapping("/")
    public BasicResponse<StudentResponse> editStudent(@RequestBody StudentRequest student) {
        final var response = new BasicResponse<StudentResponse>();
        response.setData(studentService.deleteStudent(student));
        return response;
    }
}
