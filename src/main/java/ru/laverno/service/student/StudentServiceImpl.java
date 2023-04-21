package ru.laverno.service.student;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Student;
import ru.laverno.mapper.StudentMapper;
import ru.laverno.model.student.StudentRequest;
import ru.laverno.model.student.StudentResponse;
import ru.laverno.repository.StudentRepository;
import ru.laverno.service.course.CourseService;
import ru.laverno.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserService userService;

    private final CourseService courseService;

    public StudentServiceImpl(StudentRepository studentRepository, UserService userService, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public List<Student> getAllStudents() {
        final var students = new ArrayList<Student>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public List<StudentResponse> getStudentsByStudentId(UUID studentId) {
        final var students = new ArrayList<StudentResponse>();
        studentRepository.findStudentsByUserId(studentId).forEach(student -> {
            final var user = userService.getUserById(student.getId().getUserId());
            final var course = courseService.getCourseById(student.getId().getCourseId());
            students.add(StudentMapper.studentToStudentResponse(user, course));
        });
        return students;
    }

    @Override
    public List<StudentResponse> getStudentsByCourseId(UUID courseId) {
        final var students = new ArrayList<StudentResponse>();
        studentRepository.findStudentsByCourseId(courseId).forEach(student -> {
            final var user = userService.getUserById(student.getId().getUserId());
            final var course = courseService.getCourseById(student.getId().getCourseId());
            students.add(StudentMapper.studentToStudentResponse(user, course));
        });
        return students;
    }

    @Override
    public StudentResponse addNewStudent(StudentRequest student) {
        final var course = courseService.getCourseById(student.courseId());
        final var user = userService.getUserById(student.userId());

        studentRepository.insertStudent(user.id(), course.id(), course.startTime());

        return StudentMapper.studentToStudentResponse(user, course);
    }
}
