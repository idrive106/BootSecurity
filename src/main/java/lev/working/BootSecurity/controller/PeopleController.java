package lev.working.BootSecurity.controller;

import lev.working.BootSecurity.repositories.UserRepositories;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PeopleController {

    private final UserRepositories userRepo;

    public PeopleController(UserRepositories userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String listPeople(Model model) {
        model.addAttribute("people", userRepo.findAll());
        return "admin/people-list";
    }
}