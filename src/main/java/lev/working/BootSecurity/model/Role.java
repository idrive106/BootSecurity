package lev.working.BootSecurity.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Должно содержать "ROLE_ADMIN" или "ROLE_USER"

    @Override
    public String getAuthority() {
        return name; // Возвращает "ROLE_ADMIN" или "ROLE_USER"
    }
}