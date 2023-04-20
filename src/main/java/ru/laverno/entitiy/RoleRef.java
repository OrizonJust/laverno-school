package ru.laverno.entitiy;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(schema = "school", name = "sc_t_user_x_role")
public class RoleRef {

    @Column(value = "r_id")
    private final UUID roleId;

    public RoleRef(UUID roleId) {
        this.roleId = roleId;
    }

    public UUID getRoleId() {
        return roleId;
    }
}
