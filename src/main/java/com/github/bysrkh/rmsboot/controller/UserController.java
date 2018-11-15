package com.github.bysrkh.rmsboot.controller;

import com.github.bysrkh.rmsboot.domain.Role;
import com.github.bysrkh.rmsboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.github.bysrkh.rmsboot.domain.User;
import com.github.bysrkh.rmsboot.service.UserService;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> populateRoles() {

        return roleService.findAll();
    }

    @GetMapping("/add")
    public String showCreateUserForm(User user) {
        return "user/userForm";
    }

    @GetMapping("/edit")
    public String showEditUserForm(Model model, int id) {
        User existingUser = userService.findUser(id);

        model.addAttribute("user", existingUser);

        return "user/userForm";
    }

    @GetMapping
    public String findAll(SessionStatus sesion, Model model) {
        sesion.setComplete();

        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "user/userList";
    }

    @PostMapping("/edit")
    public String processEditUserForm(SessionStatus sessionStatus, User user, BindingResult error) {
        if(error.hasErrors())
            return "user/userForm";

        userService.updateUser(user);
        sessionStatus.setComplete();

        return "redirect:/user";
    }

    @PostMapping(value = "/edit", params = "cancel")
    public String cancelEditUserForm(SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "redirect:/user";
    }


    @PostMapping(value = "/add", params = "cancel")
    public String cancelCreateUserForm(SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "redirect:/user";
    }

    @PostMapping("/add")
    public String createUser(SessionStatus sessionStatus, @Valid User user, BindingResult errors) {
        if (errors.hasErrors())
            return "user/userForm";

        userService.createUser(user);
        sessionStatus.setComplete();

        return "redirect:/user";
    }

    @GetMapping("/remove")
    public String removeUser(int id) {
        userService.deleteUser(id);

        return "redirect:/user";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}