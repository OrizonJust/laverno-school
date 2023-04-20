package ru.laverno.mapper;

import ru.laverno.entitiy.Role;
import ru.laverno.entitiy.User;
import ru.laverno.model.user.UserRequest;
import ru.laverno.model.user.UserResponse;

import java.util.List;
import java.util.Set;

public class UserMapper {

    private UserMapper() {}

    public static UserResponse userToUserResponse(User user, Set<Role> roles) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getAvatar(), user.isDisabled(), roles);
    }

    public static User userRequestToEditedUser(User user, UserRequest userRequest) {
        return new User(user.getId(),
                userRequest.username() != null ? userRequest.username() : user.getUsername(),
                userRequest.password() != null ? userRequest.password() : user.getPassword(),
                userRequest.email() != null ? userRequest.email() : user.getEmail(),
                userRequest.phone() != null ? userRequest.phone() : user.getPhone(),
                userRequest.avatar() != null ? userRequest.avatar() : user.getAvatar(),
                userRequest.disabled() != null && userRequest.disabled(),
                user.getRoles());
    }

    public static User userToDeleteUser(User user) {
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getAvatar(), true, user.getRoles());
    }
}
