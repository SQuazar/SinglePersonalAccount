package net.quazar.backend.service;

import net.quazar.backend.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);
    List<String> addPermission(Role role, String permission);
    List<String> removePermission(Role role, String permission);
    Role createRole(Role role);
    List<Role> findAllRoles();
}
