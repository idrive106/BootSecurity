package lev.working.BootSecurity.repositories;

import lev.working.BootSecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
