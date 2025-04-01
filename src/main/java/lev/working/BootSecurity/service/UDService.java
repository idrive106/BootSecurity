package lev.working.BootSecurity.service;

import lev.working.BootSecurity.model.People;
import lev.working.BootSecurity.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UDService implements UserDetailsService {
    private final UserRepositories userRepositories;

    @Autowired
    public UDService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        People user = userRepositories.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Проверяем, что роли загружены
        if (user.getAuthorities().isEmpty()) {
            throw new IllegalStateException("User has no roles assigned");
        }

        return user; // People уже реализует UserDetails
    }
}
