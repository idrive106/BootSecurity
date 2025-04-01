package lev.working.BootSecurity.repositories;


import lev.working.BootSecurity.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<People, Long> {

    Optional<People> findByName(String name);

}
