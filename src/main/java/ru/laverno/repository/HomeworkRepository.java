package ru.laverno.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.laverno.entitiy.Homework;

import java.util.UUID;

public interface HomeworkRepository extends CrudRepository<Homework, UUID> {

    @Query("select * from school.sc_t_homework h where h.s_id = :studentId")
    Iterable<Homework> findHomeworksByStudentId(@Param("studentId") UUID studentId);

    @Query("select * from school.sc_t_homework h where h.p_id = :practiceId")
    Iterable<Homework> findHomeworksByPracticeId(@Param("practiceId") UUID practiceId);

    @Modifying
    @Query("insert into school.sc_t_homework(s_id, p_id) values (:studentId, :practiceId)")
    void insertHomework(@Param("studentId") UUID studentId, @Param("practiceId") UUID practiceId);

    @Modifying
    @Query("update school.sc_t_homework h set usr_id = :watcherId where h.s_id = :studentId and h.p_id = :practiceId")
    void updateHomeworkWatcherId(@Param("watcherId") UUID watcherId, @Param("studentId") UUID studentId, @Param("practiceId") UUID practiceId);

    @Modifying
    @Query("update school.sc_t_homework h set h_done = :done where h.s_id = :studentId and h.p_id = :practiceId")
    void updateHomeworkDone(@Param("done") boolean done, @Param("studentId") UUID studentId, @Param("practiceId") UUID practiceId);
}
