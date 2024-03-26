package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.services.VoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/voli")
public class VoliController {

    @Autowired
    VoliService voliService;

    @GetMapping("/ricerca")
    public String ricercaVoli(){
        return "ricercavoli";
    }

    @GetMapping("/lista")
    public String listaVoli(@ModelAttribute("ricerca") Ricerca ricerca, Model model){
        try{
            List<VoloEntity> listaVoli = voliService.ricercaVoli(ricerca);
            model.addAttribute("listavoli",listaVoli);
        } catch(Exception e){
            return "ricercavoli_error";
        }
        return "listavoli";
    }

}
