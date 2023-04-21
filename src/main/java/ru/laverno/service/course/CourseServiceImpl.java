package ru.laverno.service.course;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Course;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.mapper.CourseMapper;
import ru.laverno.model.course.CourseRequest;
import ru.laverno.model.course.CourseResponse;
import ru.laverno.repository.CourseRepository;
import ru.laverno.service.discipline.DisciplineService;
import ru.laverno.utils.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final DisciplineService disciplineService;

    public CourseServiceImpl(CourseRepository courseRepository, DisciplineService disciplineService) {
        this.courseRepository = courseRepository;
        this.disciplineService = disciplineService;
    }

    @Override
    public CourseResponse getCourseById(UUID id) {
        final var course = courseRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_ID_EXCEPTION, Const.COURSE, id)));
        final var discipline = disciplineService.getDisciplineById(course.getDisciplineId());
        return CourseMapper.courseToCourseResponse(course, discipline);
    }

    @Override
    public CourseResponse getCourseByName(String name) {
        final var course = courseRepository.findCourseByName(name).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.NOT_FOUND_NAME_EXCEPTION, Const.COURSE, name)));
        final var discipline = disciplineService.getDisciplineById(course.getDisciplineId());
        return CourseMapper.courseToCourseResponse(course, discipline);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        final var courses = new ArrayList<CourseResponse>();
        courseRepository.findAll().forEach(course -> {
            final var discipline = disciplineService.getDisciplineById(course.getDisciplineId());
            courses.add(CourseMapper.courseToCourseResponse(course, discipline));
        });

        return courses;
    }

    @Override
    public List<CourseResponse> getActiveCourses() {
        final var courses = new ArrayList<CourseResponse>();
        courseRepository.findCoursesByDisable(false).forEach(course -> {
            final var discipline = disciplineService.getDisciplineById(course.getDisciplineId());
            courses.add(CourseMapper.courseToCourseResponse(course, discipline));
        });

        return courses;
    }

    @Override
    public List<CourseResponse> getDisableCourses() {
        final var courses = new ArrayList<CourseResponse>();
        courseRepository.findCoursesByDisable(true).forEach(course -> {
            final var discipline = disciplineService.getDisciplineById(course.getDisciplineId());
            courses.add(CourseMapper.courseToCourseResponse(course, discipline));
        });

        return courses;
    }

    @Override
    public CourseResponse addNewCourse(CourseRequest course) {
        final var newCourse = courseRepository.save(new Course(course.name(), course.startDate(), course.endDate(), false, course.disciplineId()));
        final var discipline = disciplineService.getDisciplineById(newCourse.getDisciplineId());
        return CourseMapper.courseToCourseResponse(newCourse, discipline);
    }

    @Override
    public CourseResponse deleteCourse(UUID id) {
        final var course = getCourseById(id);
        final var deletedCourse = courseRepository.save(new Course(course.id(), course.courseName(), course.startTime(), course.endTime(), true, course.disciplineId()));
        final var discipline = disciplineService.getDisciplineById(deletedCourse.getDisciplineId());
        return CourseMapper.courseToCourseResponse(deletedCourse, discipline);
    }
}
