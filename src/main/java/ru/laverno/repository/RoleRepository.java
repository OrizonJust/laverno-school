package ru.laverno.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.laverno.entitiy.Role;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> findRoleByName(String name);

    @Query("select r.r_id, r.r_name " +
            "from school.sc_t_role r " +
            "         inner join school.sc_t_user_x_role ur on r.r_id = ur.r_id " +
            "where ur.usr_id = :userId")
    Set<Role> findRolesByUserId(@Param(value = "userId") UUID userId);

    @Modifying
    @Query("insert into school.sc_t_user_x_role values (:userId, :roleId)")
    void saveUserRole(@Param(value = "userId") UUID userId, @Param(value = "roleId") UUID roleId);

    @Modifying
    @Query("delete from school.sc_t_user_x_role ur where ur.usr_id = :userId and ur.r_id = :roleId")
    void deleteUserRole(@Param(value = "userId") UUID userId, @Param(value = "roleId") UUID roleId);

}
