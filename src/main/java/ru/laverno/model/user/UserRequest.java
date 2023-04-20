package ru.laverno.model.user;

import java.util.UUID;

public record UserRequest(UUID id, String username, String password, String email, String phone, String avatar, Boolean disabled) {}
