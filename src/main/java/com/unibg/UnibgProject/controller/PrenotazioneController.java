package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.*;
import com.unibg.UnibgProject.services.LoginService;
import com.unibg.UnibgProject.services.PrenotazioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    LoginService loginService;

    @PostMapping("/crea")
    public String creaPrenotazione(@ModelAttribute("volo") Volo volo,HttpSession session, Model model) {
        session.setAttribute("id_volo",volo.getId());
        return "prenotazione/creaprenotazione";
    }

    @PostMapping("/check-in")
    public String checkIn(@ModelAttribute("prenotazione") Prenotazione prenotazione, HttpSession session,Model model) {
        try{
            //Imposto id_volo nella prenotazione (preso da session) e la salvo
            prenotazione.setId_volo((String)session.getAttribute("id_volo"));
            prenotazione.setMail((String) session.getAttribute("mail"));
            prenotazione = prenotazioneService.savePrenotazione(prenotazione);

            //salvo in sessione id_prenotazione
            session.setAttribute("id_prenotazione",prenotazione.getId());

            //Creo lista di checkin per popolare il form nel modo corretto
            List<Checkin> tempList = new ArrayList<>();
            for(int i = 0;i<Integer.parseInt(prenotazione.getNumero_biglietti());i++){
                tempList.add(new Checkin());
            }
            CheckinList checkinList = new CheckinList();
            checkinList.setCheckins(tempList);
            //aggiungo la lista al model da restituire al fe
            model.addAttribute("checkinList",checkinList);
        } catch(Exception e){
            return "error";
        }
        return "prenotazione/checkin";
    }

    @PostMapping("/success")
    public String saveCheckin(@ModelAttribute("checkinList") CheckinList checkinList, HttpSession session, Model model) {
        try{
            String mail=(String) session.getAttribute("mail");
            String idPrenotazione = (String) session.getAttribute("id_prenotazione");
            prenotazioneService.saveCheckin(checkinList.getCheckins(),mail,idPrenotazione);
            UtenteEntity utenteEntity = loginService.findByMail(mail);
            Utente utente = new Utente();
            BeanUtils.copyProperties(utenteEntity,utente);
            model.addAttribute(utente);
        } catch(Exception e){
            return "error";
        }
        return "login/profilehomepage";
    }

    @GetMapping("/visualizza")
    public String visualizzaPrenotazioni(HttpSession session, Model model){
        List<Volo> listaPrenotazioni = prenotazioneService.getVoliByIdPrenotazione((String) session.getAttribute("mail"));
        model.addAttribute(listaPrenotazioni);

        return "visualizzaprenotazioni";
    }

}
