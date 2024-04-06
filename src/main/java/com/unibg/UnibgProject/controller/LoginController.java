package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @GetMapping("")
    public String homePage(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/registrazione")
    public String registrazione() {
        return "login/registrazione";
    }

    @PostMapping("/registrazioneform")
    public String registrazioneForm(@ModelAttribute("utente") Utente utente, Model model) {
        try {
            UtenteEntity utenteEntity = loginService.saveRegistrazione(utente);
            return "login/registrazionesuccess";
        } catch(Exception e){
            return "error";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/profilehomepage")
    public String loginForm(@ModelAttribute("utente") Utente utente, Model model, HttpSession session) {
        try{
            UtenteEntity utenteEntity;
            if(session.getAttribute("mail")==null) {
                utenteEntity = loginService.login(utente);
                session.setAttribute("mail", utente.getMail());
            } else{
                utenteEntity = loginService.findByMail( (String) session.getAttribute("mail"));
            }
            model.addAttribute("utente", utenteEntity);
        } catch(Exception e){
            model.addAttribute("error","Errore di login.");
            return "error";
        }
        return "login/profilehomepage";
    }


}
