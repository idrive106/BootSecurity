package lev.working.BootSecurity.config;

import lev.working.BootSecurity.models.Role;
import lev.working.BootSecurity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Создание стандартных ролей
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        // Сохранение ролей в базу данных
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }
}
