package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.AnagraficaService;
import com.unibg.UnibgProject.utils.UtilsGeneric;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    AnagraficaService anagraficaService;

    @PostMapping("/modifica")
    public String modificaAnagrafica(@ModelAttribute Utente utente, Model model, HttpSession session) {
        if (!UtilsGeneric.isSessionActive(session)) {
            return "error";
        }

        UtenteEntity utenteEntity = anagraficaService.modificaAnagrafica(utente);
        session.setAttribute("utente", utenteEntity);
        model.addAttribute("utente", utenteEntity);

        return "modificaAnagraficaSuccess";
    }

    @PostMapping("/elimina")
    public String eliminaAnagrafica(@ModelAttribute Utente utente, Model model, HttpSession session) {
        if (!UtilsGeneric.isSessionActive(session)) {
            return "error";
        }

        UtenteEntity utenteEntity = (UtenteEntity) session.getAttribute("utente");
        if (anagraficaService.eliminaAnagrafica(utenteEntity)) {
            System.out.println("Utente eliminato con successo");
        } else {
            System.out.println("Errore durante eliminazione dell'utente");
        }

        return "eliminaAnagraficaSuccess";
    }

}
