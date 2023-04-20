package ru.laverno.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.laverno.entitiy.Role;

import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(UUID id, String username, String email, String phone, String avatar, boolean disabled, Set<Role> roles) {}
