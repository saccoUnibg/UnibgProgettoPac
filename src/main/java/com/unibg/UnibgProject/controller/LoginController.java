package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("")
    public String homePage() {
        return "homepage";
    }

    @GetMapping("/registrazione")
    public String registrazione() {
        return "registrazione";
    }


    @PostMapping("/registrazioneform")
    public String registrazioneSent(@ModelAttribute("utente") Utente utente) {
        UtenteEntity utenteEntity = new UtenteEntity();
        try{
            BeanUtils.copyProperties(utente,utenteEntity);
            utenteRepository.save(utenteEntity);
            return "registrazionesuccess";
        }catch (Exception e){
            return "registrazionefail";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

/*
relazione d'azienda (documento) --> condividere database con prof.ssa
+ ricreare situazioni
inserisco prodotti, clienti, acquisto e vendita materiale,
 */