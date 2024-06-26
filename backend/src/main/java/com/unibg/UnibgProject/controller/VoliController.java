package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.VoliService;
import com.unibg.UnibgProject.utils.Coppia;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/voli")
public class VoliController {

    @Autowired
    VoliService voliService;

    @GetMapping("/lista")
    public ResponseEntity<?> listaVoli(@RequestParam String partenza,
                                       @RequestParam String arrivo,
                                       @RequestParam String data,
                                       @RequestParam String scalo,
                                       @RequestParam(required = false) String scalo_min,
                                       @RequestParam(required = false) String scalo_max,
                                       HttpSession session){

        log.info("listaVoli Request:{} {} {} {} ",partenza,arrivo,data,scalo);

        Ricerca ricerca = new Ricerca(partenza,arrivo,data,(String) session.getAttribute("mail"),scalo,scalo_min,scalo_max);
        try{
            ricerca.setMail((String) session.getAttribute("mail"));

            // Implementazione di due metodi differenti per semplificare le operazioni di modifica su entrambi i metodi (NO long method)
            if(ricerca.getScalo().equalsIgnoreCase("false")){
                List<Volo> listaVoli = voliService.ricercaVoli(ricerca);
                return ResponseEntity.status(HttpStatus.OK).body(listaVoli);
            } else{
                List<Coppia<Volo,Volo>> listaVoli = voliService.ricercaVoliScalo(ricerca);
                return ResponseEntity.status(HttpStatus.OK).body(listaVoli);
            }
        } catch(Exception e){
            log.error("Error in listaVoli: ",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }

    @PostMapping("/crea")
    public ResponseEntity<?> creaVolo(@RequestBody Volo volo){
        try {
            if (!volo.getPartenza().equals(volo.getArrivo())){
                Volo voloCreato = voliService.creaVolo(volo);
                return ResponseEntity.status(HttpStatus.OK).body(voloCreato);
            }else {
                log.error("Partenza e destinazione uguali");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } catch (Exception e) {
            log.error("Error in creaVolo: ",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

    }

}
