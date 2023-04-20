package ru.laverno.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.laverno.entitiy.Role;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.model.BasicResponse;
import ru.laverno.service.role.RoleService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    public final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public BasicResponse<Set<Role>> getRoles() {
        final var response = new BasicResponse<Set<Role>>();
        try {
            response.setData(roleService.getRoles());
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/user/{id}")
    public BasicResponse<Set<Role>> getRolesByUserId(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<Set<Role>>();
        try {
            final var uuid = UUID.fromString(id);
            response.setData(roleService.getRolesByUserId(uuid));
        } catch (IllegalArgumentException ex) {
            response.setFault("Получен некорректный [id=%s]!", id);
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }
}
