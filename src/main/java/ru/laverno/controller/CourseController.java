package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.entitiy.Course;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.course.CourseRequest;
import ru.laverno.model.course.CourseResponse;
import ru.laverno.service.course.CourseService;
import ru.laverno.service.course.CourseServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public BasicResponse<List<CourseResponse>> getAllCourses() {
        final var response = new BasicResponse<List<CourseResponse>>();
        response.setData(courseService.getAllCourses());
        return response;
    }

    @GetMapping("/active")
    public BasicResponse<List<CourseResponse>> getActiveCourses() {
        final var response = new BasicResponse<List<CourseResponse>>();
        response.setData(courseService.getActiveCourses());
        return response;
    }

    @GetMapping("/disable")
    public BasicResponse<List<CourseResponse>> getDisableCourses() {
        final var response = new BasicResponse<List<CourseResponse>>();
        response.setData(courseService.getDisableCourses());
        return response;
    }

    @GetMapping("/{id}")
    public BasicResponse<CourseResponse> getCourseById(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<CourseResponse>();
        response.setData(courseService.getCourseById(UUID.fromString(id)));
        return response;
    }

    @GetMapping("/name/{name}")
    public BasicResponse<CourseResponse> getCourseByName(@PathVariable(name = "name") final String name) {
        final var response = new BasicResponse<CourseResponse>();
        response.setData(courseService.getCourseByName(name));
        return response;
    }

    @PostMapping("/")
    public BasicResponse<CourseResponse> addNewCourse(@RequestBody CourseRequest course) {
        final var response = new BasicResponse<CourseResponse>();
        response.setData(courseService.addNewCourse(course));
        return response;
    }

    @DeleteMapping("/{id}")
    public BasicResponse<CourseResponse> deleteCourse(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<CourseResponse>();
        response.setData(courseService.deleteCourse(UUID.fromString(id)));
        return response;
    }
}
