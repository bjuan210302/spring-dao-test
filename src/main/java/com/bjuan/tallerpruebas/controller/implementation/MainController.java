package com.bjuan.tallerpruebas.controller.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjuan.tallerpruebas.model.user.MyUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/dashboard")
    public String dash(){
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new MyUser());
        return "log/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
    }
        return "redirect:/login?logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "log/access-denied";
    }

}
