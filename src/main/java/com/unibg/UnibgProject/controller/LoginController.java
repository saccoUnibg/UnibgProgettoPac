package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registrazioneForm(@ModelAttribute("utente") Utente utente, Model model) {
        UtenteEntity utenteEntity = new UtenteEntity();
        try {
            BeanUtils.copyProperties(utente,utenteEntity);
            utenteRepository.save(utenteEntity);
            model.addAttribute(utente);
            return "registrazionesuccess";
        } catch (DataIntegrityViolationException e) {
            if(utenteRepository.findByMail(utenteEntity.getMail()) !=null) {
                model.addAttribute("error","Errore nella registrazione dell'utenza: mail già registrata.");
            } else {
                model.addAttribute("error","Errore nella registrazione dell'utenza: contattare assistenza.");
            }
            return "registrazionefail";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginform")
    public String loginForm(@ModelAttribute("utente") Utente utente, Model model) {
        UtenteEntity utenteEntity = new UtenteEntity();
        try{
            BeanUtils.copyProperties(utente,utenteEntity);
            utenteEntity = utenteRepository.findByMailAndPsw(utente.getMail(), utenteEntity.getPsw());

            model.addAttribute("utente",utenteEntity);
        } catch(Exception e){
            model.addAttribute("error","Errore di login.");
        }
        return "profilehomepage";
    }
}

/*
relazione d'azienda (documento) --> condividere database con prof.ssa
+ ricreare situazioni
inserisco prodotti, clienti, acquisto e vendita materiale,
 */