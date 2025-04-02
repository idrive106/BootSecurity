package lev.working.BootSecurity.service;


import lev.working.BootSecurity.config.SecurityConfig;
import lev.working.BootSecurity.models.Role;
import lev.working.BootSecurity.models.User;
import lev.working.BootSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService{

    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> foundPerson = userRepository.findById(id);
        return foundPerson.orElse(null);
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public void save(User user, List<Role> roles) {
        if (userRepository.findByName(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with this login was not found!");
        }
        if (user.getRoles().isEmpty()) {
            user.setRoles(roleService.defaultRole());
        } else {
            user.setRoles(roles);
        }

        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(User updatedUser, List<Long> roles ,Long id) {
        User updUser = findById(id);
        updUser.setName(updatedUser.getName());
        updUser.setFunction(updatedUser.getFunction());
        updUser.setSalary(updatedUser.getSalary());
        updUser.setPassword(SecurityConfig.passwordEncoder().encode(updatedUser.getPassword()));
        updUser.getRoles().clear();
        updUser.setRoles(roleService.findRoleById(roles));
        userRepository.save(updUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}