package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @PostMapping("/crea")
    public String creaPrenotazione(@ModelAttribute("volo") Volo volo, Model model) {
        model.addAttribute(volo);
        return "creaprenotazione";
    }

    @PostMapping("/check-in")
    public String checkIn(@ModelAttribute("prenotazione") Prenotazione prenotazione, Model model) {
        try{
            //salvo la prenotazione e prelevo l'id da portare nel check-in
            String id_prenotazione = String.valueOf(prenotazioneService.savePrenotazione(prenotazione));
            prenotazione.setId(id_prenotazione);
            model.addAttribute(prenotazione);

        } catch(Exception e){
            return "error";
        }
        return "checkin".concat(prenotazione.getNumero_biglietti());
    }

    @PostMapping("/success")
    public String saveCheckin(@ModelAttribute("checkin") Checkin checkin, Model model) {
        try{
            prenotazioneService.saveCheckin(checkin);
        } catch(Exception e){
            return "error";
        }
        return "profilehomepage";
    }
}
