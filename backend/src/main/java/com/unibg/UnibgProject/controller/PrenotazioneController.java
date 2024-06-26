package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.*;
import com.unibg.UnibgProject.services.LoginService;
import com.unibg.UnibgProject.services.PrenotazioneService;
import com.unibg.UnibgProject.services.VoliService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;
    @Autowired
    LoginService loginService;
    @Autowired
    VoliService voliService;

    @PostMapping("/crea")
    public ResponseEntity<?> creaPrenotazione(@RequestBody Volo volo, HttpSession session) {
        session.setAttribute("id_volo",volo.getId());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@RequestBody Prenotazione prenotazione, HttpSession session) {
        try{
            //Imposto id_volo nella prenotazione (preso da session) e la salvo
            prenotazione.setIdVolo((String)session.getAttribute("id_volo"));
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
            checkinList.setCheckinList(tempList);
            //aggiungo la lista al model da restituire al fe
            return ResponseEntity.status(HttpStatus.OK).body(checkinList);
        } catch(Exception e){
            log.error("Error in checkIn: ",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }

    @PostMapping("/success")
    public ResponseEntity<?> saveCheckin(@ModelAttribute("checkinList") CheckinList checkinList, HttpSession session, Model model) {
        try{
            String mail=(String) session.getAttribute("mail");
            String idPrenotazione = (String) session.getAttribute("id_prenotazione");
            prenotazioneService.saveCheckin(checkinList.getCheckinList(),mail,idPrenotazione);
            Utente utente = loginService.findByMail(mail);
            return ResponseEntity.status(HttpStatus.OK).body(utente);
        } catch(Exception e){
            log.error("Error in saveCheckin: ",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }

    @GetMapping("/visualizza")
    public ResponseEntity<?> visualizzaPrenotazioni(HttpSession session, Model model){
        try{
            //Ottengo lista delle prenotazioni effettuate da una utenza via mail
            List<Prenotazione> prenotazioneList =
                    prenotazioneService.getVoliPrenotatiByMail((String)session.getAttribute("mail"));

            // Ottengo i dettagli dei voli delle prenotazioni effettuate
            List<Volo> listaVoli =
                    voliService.getVoliByPrenotazioni(prenotazioneList);

            // Mappa con associazione id volo e id prenotazione per non doverlo recuperare dopo da db con altre queries
            Map<String,String> idPrenotazioniAndVoli = new HashMap<>();
            for(Prenotazione temp: prenotazioneList){
                idPrenotazioniAndVoli.put(temp.getIdVolo(),temp.getId());
            }
            session.setAttribute("idPrenotazioniAndVoli",idPrenotazioniAndVoli);

            return ResponseEntity.status(HttpStatus.OK).body(listaVoli);
        } catch (Exception e){
            log.error("Error in visualizzaPrenotazioni: ",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }

    @PostMapping("/elimina")
    public ResponseEntity<?> eliminaPrenotazione(@ModelAttribute("volo") Volo volo, HttpSession session, Model model){

        // una volta confermato, cancello tutto ciò che è legato alla mail per quel id_volo (prenotazione + checkIn di ogni persona)
        Long idVolo = volo.getId();
        session.setAttribute("id_volo",idVolo);

        model.addAttribute("volo",volo);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/elimina/conferma")
    public ResponseEntity<?> confermaEliminaPrenotazione(HttpSession session,Model model){
        try{
            // 1. recupero informazioni dalla sessione
            String idVolo = (String) session.getAttribute("id_volo");

            // chiave: idPrenotazione ; valore: idVolo
            Map<String,String> idPrenotazioniAndVoli = (HashMap<String,String>) session.getAttribute("idPrenotazioniAndVoli");

            // 2. recupero idPrenotazione dalla map con idVolo ( non filtro per mail, perchè la mappa e' gia' filtrata per mail utenza)
            String idPrenotazione = idPrenotazioniAndVoli.get(idVolo);
            prenotazioneService.deletePrenotazione(idPrenotazione);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
