package com.unibg.UnibgProject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String mainPage(Model model){
        return "main";
    }
    @GetMapping("/welcome")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/prova")
    public String prova(){
        return "Welcome";
    }
}

/*
@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
}
 */