package com.github.bysrkh.rmsboot.controller;

import com.github.bysrkh.rmsboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginForm(User user) {

        return "login/loginForm";
    }
}
