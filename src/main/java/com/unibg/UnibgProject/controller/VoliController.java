package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.VoliService;
import com.unibg.UnibgProject.utils.ApiResponse;
import com.unibg.UnibgProject.utils.ApiResponseCodes;
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
            List<Volo> listaVoli = new ArrayList<>();

            // Implementazione di due metodi differenti per semplificare le operazioni di modifica su entrambi i metodi (NO long method)
            if(ricerca.getScalo().equalsIgnoreCase("false")){
                listaVoli = voliService.ricercaVoli(ricerca);
            } else{
                listaVoli = voliService.ricercaVoliScalo(ricerca);
            }

            response.setObject(listaVoli, ApiResponseCodes.SUCCESS);
        } catch(Exception e){
            response.setObject(null, ApiResponseCodes.ERROR);
            response.setMessage(e.toString());
        }
        return response;
    }

}
