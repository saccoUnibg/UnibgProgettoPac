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
    public String homePage() {
        return "index";
    }

    @GetMapping("/registrazione")
    public String registrazione() {
        return "registrazione";
    }

    @PostMapping("/registrazioneform")
    public String registrazioneForm(@ModelAttribute("utente") Utente utente, Model model) {
        try {
            UtenteEntity utenteEntity = loginService.saveRegistrazione(utente);
            model.addAttribute("utente",utenteEntity);
            return "registrazionesuccess";
        } catch(Exception e){
            model.addAttribute("error",e.toString());
            return "registrazionefail";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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
            return "loginerror";
        }
        return "profilehomepage";
    }
}

/*
relazione d'azienda (documento) --> condividere database con prof.ssa
+ ricreare situazioni
inserisco prodotti, clienti, acquisto e vendita materiale, etc.
 */