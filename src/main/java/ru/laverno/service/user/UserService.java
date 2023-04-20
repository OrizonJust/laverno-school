package ru.laverno.service.user;

import ru.laverno.model.role.RoleRequest;
import ru.laverno.model.user.UserRequest;
import ru.laverno.model.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse getUserById(final UUID id);

    UserResponse getUserByEmail(final String email);

    UserResponse getUserByPhone(final String phone);

    List<UserResponse> getUsersByUsername(final String username);

    List<UserResponse> getAllUsers();

    UserResponse addNewUser(final UserRequest user);

    UserResponse editUser(final UserRequest user);

    boolean deleteUser(final UUID id);

    UserResponse addNewRole(RoleRequest roleRequest);

    UserResponse deleteUserRole(RoleRequest roleRequest);
}
