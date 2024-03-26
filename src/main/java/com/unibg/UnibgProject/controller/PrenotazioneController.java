package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Volo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {


    @PostMapping("/crea")
    public String creaPrenotazione(@ModelAttribute("volo") Volo volo, Model model) {
        model.addAttribute(volo);
        return "creaprenotazione";
    }
}
