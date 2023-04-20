package ru.laverno.service.role;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.Role;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(()
                -> new DataNotFoundException(String.format("Не удалось найти роль с именем [name=%s]", name)));
    }

    @Override
    public Set<Role> getRoles() throws DataNotFoundException {
        final var roles = new HashSet<Role>();
        roleRepository.findAll().forEach(roles::add);

        if (roles.isEmpty()) {
            throw new DataNotFoundException("Не удалось найти роли!");
        }
        return roles;
    }

    @Override
    public Set<Role> getRolesByUserId(UUID userId) throws DataNotFoundException {
        final var roles = roleRepository.findRolesByUserId(userId);

        if (roles.isEmpty()) {
            throw new DataNotFoundException(String.format("Не удалось найти пользователя по идентификатору [id=%s]!", userId));
        }
        return roles;
    }

    @Override
    public void addNewRole(UUID userId, UUID roleId) {
        try {
            roleRepository.saveUserRole(userId, roleId);
        } catch (Exception ex) {
            throw new DataAlreadyExistsException(String.format("Связка ключей уже существует: [userId=%s]; [roleId=%s]!", userId, roleId));
        }
    }

    @Override
    public void deleteUserRole(UUID userId, UUID roleId) {
        roleRepository.deleteUserRole(userId, roleId);
    }
}
