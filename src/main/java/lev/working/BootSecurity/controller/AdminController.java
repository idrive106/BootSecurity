package lev.working.BootSecurity.controller;

import lev.working.BootSecurity.models.User;
import lev.working.BootSecurity.service.RoleService;
import lev.working.BootSecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller

public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/index")
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        return "index";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) List<Long> rolesId,
                             @RequestParam(value = "id") Long id)
    {
        roleService.findRoleById(rolesId);
        user.setRoles(roleService.findRoleById(rolesId));
        userService.update(user, rolesId, id);
        return "redirect:/index";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        userService.delete(id);
        return "redirect:index";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("role_all", roleService.getRoles());
        return "update";
    }

    @GetMapping("/admin")
    public String showAdminForm(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "admin";
    }
}
