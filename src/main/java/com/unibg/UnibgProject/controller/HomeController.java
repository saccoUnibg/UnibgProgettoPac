package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    WelcomeService welcomeService;

    @GetMapping("")
    public String mainPage(Model model){
        return "main";
    }

    @GetMapping("/welcome")
    public String homePage(@RequestParam String nome, @RequestParam String cognome) {

        String prova = welcomeService.getHello(nome,cognome);
        return "home";
    }

    @GetMapping("/prova")
    public String prova(){
        return "Welcome";
    }
}
