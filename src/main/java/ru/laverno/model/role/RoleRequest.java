package ru.laverno.model.role;

import java.util.UUID;

public record RoleRequest(UUID userId, UUID roleId) {}
