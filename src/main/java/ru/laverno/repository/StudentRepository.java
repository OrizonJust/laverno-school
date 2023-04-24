package ru.laverno.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.laverno.entitiy.Student;
import ru.laverno.model.EmbeddedUserCourseId;

import java.time.LocalDate;
import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, EmbeddedUserCourseId> {

    @Query("select * from school.sc_t_student s where s.usr_id = :userId")
    Iterable<Student> findStudentsByUserId(@Param(value = "userId") UUID userId);

    @Query("select * from school.sc_t_student s where s.c_id = :courseId")
    Iterable<Student> findStudentsByCourseId(@Param(value = "courseId") UUID courseId);

    @Modifying
    @Query("insert into school.sc_t_student(usr_id, c_id, st_start_date) values (:userId, :courseId, :startDate)")
    void insertStudent(@Param(value = "userId") UUID userId, @Param(value = "courseId") UUID courseId, @Param(value = "startDate") LocalDate startDate);

    @Modifying
    @Query("update school.sc_t_speaker sp set sp_disable = false where sp.usr_id = :userId and sp.c_id = :courseId")
    void deleteStudentById(@Param(value = "userId") UUID userId, @Param(value = "courseId") UUID courseId);
}
