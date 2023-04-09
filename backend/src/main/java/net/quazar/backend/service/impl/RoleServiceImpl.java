package net.quazar.backend.service.impl;

import lombok.AllArgsConstructor;
import net.quazar.backend.entity.Role;
import net.quazar.backend.exception.RoleNotFoundException;
import net.quazar.backend.repository.RoleRepository;
import net.quazar.backend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new RoleNotFoundException(String.format("Role with name %s isn't found", name)));
    }

    @Override
    public List<String> addPermission(Role role, String permission) {
        role.getPermissions().add(permission);
        return repository.save(role).getPermissions().stream().toList();
    }

    @Override
    public List<String> removePermission(Role role, String permission) {
        role.getPermissions().remove(permission);
        return repository.save(role).getPermissions().stream().toList();
    }

    @Override
    public Role createRole(Role role) {
        return repository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return repository.findAll();
    }
}
