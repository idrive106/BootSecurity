package lev.working.BootSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }
}