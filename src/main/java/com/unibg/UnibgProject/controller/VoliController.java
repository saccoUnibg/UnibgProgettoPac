package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.VoliService;
import com.unibg.UnibgProject.utils.ApiResponse;
import com.unibg.UnibgProject.utils.ApiResponseCodes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voli")
public class VoliController {

    @Autowired
    VoliService voliService;

    @GetMapping("/lista")
    public ApiResponse listaVoli(@RequestParam String partenza, @RequestParam String arrivo, @RequestParam String data, HttpSession session){
        ApiResponse response = new ApiResponse();
        Ricerca ricerca = new Ricerca(partenza,arrivo,data,(String) session.getAttribute("mail"));
        try{
            ricerca.setMail((String) session.getAttribute("mail"));
            List<Volo> listaVoli = voliService.ricercaVoli(ricerca);
            response.setObject(listaVoli, ApiResponseCodes.SUCCESS);
        } catch(Exception e){
            response.setObject(null, ApiResponseCodes.ERROR);
        }
        return response;
    }

}
