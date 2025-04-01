package lev.working.BootSecurity.service;

import lev.working.BootSecurity.model.User;
import lev.working.BootSecurity.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UDService implements UserDetailsService {
    private final UserRepositories userRepositories;

    @Autowired
    public UDService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.findByName(name);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return user.get();
    }
}
