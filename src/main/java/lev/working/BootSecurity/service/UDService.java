package lev.working.BootSecurity.service;

import lev.working.BootSecurity.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service

public class UDService implements UserDetailsService {
    private final UserRepositories userRepositories;

    @Autowired
    public UDService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        return userRepositories.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
