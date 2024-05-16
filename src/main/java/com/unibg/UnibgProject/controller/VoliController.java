package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.VoliService;
import com.unibg.UnibgProject.utils.ApiResponse;
import com.unibg.UnibgProject.utils.ApiResponseCodes;
import com.unibg.UnibgProject.utils.Coppia;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voli")
public class VoliController {

    @Autowired
    VoliService voliService;

    @GetMapping("/lista")
    public ApiResponse listaVoli(@RequestParam String partenza,
                                    @RequestParam String arrivo,
                                    @RequestParam String data,
                                    @RequestParam String scalo,
                                    @RequestParam(required = false) String scalo_min,
                                    @RequestParam(required = false) String scalo_max,
                                    HttpSession session){

        ApiResponse response = new ApiResponse();
        Ricerca ricerca = new Ricerca(partenza,arrivo,data,(String) session.getAttribute("mail"),scalo,scalo_min,scalo_max);
        try{
            ricerca.setMail((String) session.getAttribute("mail"));

            // Implementazione di due metodi differenti per semplificare le operazioni di modifica su entrambi i metodi (NO long method)
            if(ricerca.getScalo().equalsIgnoreCase("false")){
                List<Volo> listaVoli = voliService.ricercaVoli(ricerca);
                response.setObject(listaVoli);
            } else{
                List<Coppia<VoloEntity,VoloEntity>> listaVoli = voliService.ricercaVoliScalo(ricerca);
                response.setObject(listaVoli);
            }
            response.setMessage("listaVoli API call: success");
        } catch(Exception e){
            response.setErrorMessage(e.toString());
        }
        return response;
    }

}
