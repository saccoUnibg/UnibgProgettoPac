package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.services.VoliService;
import jakarta.servlet.http.HttpSession;
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
        return "voli/ricercavoli";
    }

    @GetMapping("/lista")
    public String listaVoli(@ModelAttribute("ricerca") Ricerca ricerca, HttpSession session, Model model){
        try{
            ricerca.setMail((String) session.getAttribute("mail"));
            List<Volo> listaVoli = voliService.ricercaVoli(ricerca);
            model.addAttribute("listavoli",listaVoli);
        } catch(Exception e){
            return "voli/ricercavoli_error";
        }
        return "voli/listavoli";
    }

}
