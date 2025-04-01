package lev.working.BootSecurity.controller;


import lev.working.BootSecurity.model.User;
import lev.working.BootSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleController {

    private final UserService userService;

    @Autowired
    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("people", userService.findAll());
        return "/index";
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/add";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.save(user);
            return "redirect:/users";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Ошибка при добавлении пользователя");
            return "/add";
        }
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userService.findOne(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "/edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user, @RequestParam Long id) {
        try {
            userService.update(id,user);
        } catch (RuntimeException e) {

            return "/edit";
        }
        return "redirect:/users";
    }
}