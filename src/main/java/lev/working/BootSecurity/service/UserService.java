package lev.working.BootSecurity.service;

import lev.working.BootSecurity.model.People;
import lev.working.BootSecurity.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService{

    private final UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public List<People> findAll() {
        return userRepositories.findAll();
    }

    public People findById(Long id) {
        Optional<People> foundPerson = userRepositories.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(People people) {
        userRepositories.save(people);
    }

    @Transactional
    public void update(Long id, People updatedPeople) {
        updatedPeople.setId(id);
        userRepositories.save(updatedPeople);
    }

    @Transactional
    public void delete(Long id) {
        userRepositories.deleteById(id);
    }

    @Transactional
    public People findByUsername(String username) {
        return userRepositories.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}