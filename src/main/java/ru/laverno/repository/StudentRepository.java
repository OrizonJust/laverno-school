package ru.laverno.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.laverno.entitiy.Student;

import java.time.LocalDate;
import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, Student.EmbeddedStudentId> {

    @Query("select * from school.sc_t_student s where s.usr_id = :id")
    Iterable<Student> findStudentsByUserId(@Param(value = "id") UUID id);

    @Query("select * from school.sc_t_student s where s.c_id = :id")
    Iterable<Student> findStudentsByCourseId(@Param(value = "id") UUID id);

    @Modifying
    @Query("insert into school.sc_t_student(usr_id, c_id, s_start_date) values (:userId, :courseId, :startDate)")
    void insertStudent(@Param(value = "userId") UUID userId, @Param(value = "courseId") UUID courseId, @Param(value = "startDate") LocalDate startDate);
}
