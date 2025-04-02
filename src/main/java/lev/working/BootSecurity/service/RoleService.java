package lev.working.BootSecurity.service;

import lev.working.BootSecurity.models.Role;
import lev.working.BootSecurity.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findRoleById(List<Long> id) {
        return roleRepository.findAllById(id);
    }

    public List<Role> defaultRole() {
        Role defaultRole = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Default role not found"));
        return Collections.singletonList(defaultRole);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
