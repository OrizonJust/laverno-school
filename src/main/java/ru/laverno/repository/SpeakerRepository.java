package ru.laverno.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.laverno.entitiy.Speaker;
import ru.laverno.model.EmbeddedUserCourseId;

import java.time.LocalDate;
import java.util.UUID;

public interface SpeakerRepository extends CrudRepository<Speaker, EmbeddedUserCourseId> {

    @Query("select * from school.sc_t_speaker sp where sp.usr_id = :userId")
    Iterable<Speaker> getSpeakersByUserId(@Param(value = "userId") UUID userId);

    @Query("select * from school.sc_t_speaker sp where sp.c_id = :courseId")
    Iterable<Speaker> getSpeakersByCourseId(@Param(value = "courseId") UUID courseId);

    @Modifying
    @Query("insert into school.sc_t_speaker(usr_id, c_id, sp_start_date) values (:userId, :courseId, :startDate)")
    void insertSpeaker(@Param(value = "userId") UUID userId, @Param(value = "courseId") UUID courseId, @Param(value = "startDate") LocalDate startDate);

    @Modifying
    @Query("update school.sc_t_speaker sp set sp_disable = true where sp.usr_id = :userId and sp.c_id = :courseId")
    void deleteSpeakerById(@Param(value = "userId") UUID userId, @Param(value = "courseId") UUID courseId);
}
