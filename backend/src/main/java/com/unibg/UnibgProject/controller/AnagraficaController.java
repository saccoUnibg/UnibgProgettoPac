package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.AnagraficaService;
import com.unibg.UnibgProject.utils.UtilsGeneric;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    AnagraficaService anagraficaService;

    @PostMapping("/modifica")
    public ResponseEntity<?> modificaAnagrafica(@RequestBody Utente utente, HttpSession session) {
        if (!UtilsGeneric.isSessionActive(session)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Inactive Session");
        }

        try{
            Utente utenteModificato = anagraficaService.modificaAnagrafica(utente);
            session.setAttribute("utente", utenteModificato);
            return ResponseEntity.status(HttpStatus.OK).body(utenteModificato);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

    }

    @PostMapping("/elimina")
    public ResponseEntity<?> eliminaAnagrafica(@RequestBody Utente utente, HttpSession session) {
        if (!UtilsGeneric.isSessionActive(session)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Inactive Session");
        }

        if (anagraficaService.eliminaAnagrafica(utente)) {
            log.info("Utente eliminato con successo");
            session.removeAttribute("utente");
            session.removeAttribute("mail");
        } else {
            log.error("Errore durante eliminazione dell'utente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
