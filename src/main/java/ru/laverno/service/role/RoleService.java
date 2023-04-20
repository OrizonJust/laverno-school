package ru.laverno.service.role;

import ru.laverno.entitiy.Role;
import ru.laverno.exception.DataNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface RoleService {

    Role getRoleByName(String name) throws DataNotFoundException;

    Set<Role> getRoles() throws DataNotFoundException;

    Set<Role> getRolesByUserId(UUID userId) throws DataNotFoundException;

    void addNewRole(UUID userId, UUID roleId);

    void deleteUserRole(UUID userId, UUID roleId);
}
