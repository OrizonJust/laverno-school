package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.exception.ParamValidationException;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.role.RoleRequest;
import ru.laverno.model.user.UserRequest;
import ru.laverno.model.user.UserResponse;
import ru.laverno.service.user.UserService;
import ru.laverno.utils.ParamValidator;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public BasicResponse<List<UserResponse>> getUsers() {
        final var response = new BasicResponse<List<UserResponse>>();
        try {
            response.setData(userService.getAllUsers());
        } catch (RuntimeException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/{id}")
    public BasicResponse<UserResponse> getUserById(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<UserResponse>();
        try {
            response.setData(userService.getUserById(UUID.fromString(id)));
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/name/{name}")
    public BasicResponse<List<UserResponse>> getUsersByUsername(@PathVariable(name = "name") final String name) {
        final var response = new BasicResponse<List<UserResponse>>();
        try {
            response.setData(userService.getUsersByUsername(name));
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/email/{email}")
    public BasicResponse<UserResponse> getUserByEmail(@PathVariable(name = "email") final String email) {
        final var response = new BasicResponse<UserResponse>();
        try {
            ParamValidator.validateEmail(email);
            response.setData(userService.getUserByEmail(email));
        } catch (DataNotFoundException | ParamValidationException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/phone/{phone}")
    public BasicResponse<UserResponse> getUserByPhone(@PathVariable(name = "phone") final String phone) {
        final var response = new BasicResponse<UserResponse>();
        try {
            ParamValidator.validatePhone(phone);
            response.setData(userService.getUserByPhone(phone));
        } catch (DataNotFoundException | ParamValidationException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/")
    public BasicResponse<UserResponse> createUser(@RequestBody final UserRequest user) {
        final var response = new BasicResponse<UserResponse>();

        try {
            response.setData(userService.addNewUser(user));
        } catch (ParamValidationException | DataAlreadyExistsException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @PutMapping("/")
    public BasicResponse<UserResponse> editUser(@RequestBody final UserRequest user) {
        final var response = new BasicResponse<UserResponse>();
        try {
            response.setData(userService.editUser(user));
        } catch (DataNotFoundException | ParamValidationException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public BasicResponse<Boolean> deleteUser(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<Boolean>();
        try {
            response.setData(userService.deleteUser(UUID.fromString(id)));
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/role")
    public BasicResponse<UserResponse> addNewRole(@RequestBody RoleRequest roleRequest) {
        final var response = new BasicResponse<UserResponse>();
        try {
            response.setData(userService.addNewRole(roleRequest));
        } catch (DataAlreadyExistsException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @DeleteMapping("/role")
    public BasicResponse<UserResponse> deleteUserRole(@RequestBody RoleRequest roleRequest) {
        final var response = new BasicResponse<UserResponse>();
        try {
            response.setData(userService.deleteUserRole(roleRequest));
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }
        return response;
    }
}
