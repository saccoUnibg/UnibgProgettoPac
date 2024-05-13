package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.LoginService;
import com.unibg.UnibgProject.utils.ApiResponse;
import com.unibg.UnibgProject.utils.ApiResponseCodes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/registrazioneform")
    public ApiResponse registrazioneForm(@RequestBody Utente utente) {
        ApiResponse response = new ApiResponse();
        try {
            utente = loginService.saveRegistrazione(utente);
            response.setObject(utente);
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/profilehomepage")
    public ApiResponse loginForm(@RequestBody Utente utente, HttpSession session) {
        ApiResponse response = new ApiResponse();
        try {
            if (session.getAttribute("mail") == null) {
                utente = loginService.login(utente);
                if (utente == null) {
                    return null;
                } else {
                    session.setAttribute("mail", utente.getMail());
                }
            } else {
                utente = loginService.findByMail((String) session.getAttribute("mail"));
            }
            session.setAttribute("utente", utente);
            response.setObject(utente, ApiResponseCodes.SUCCESS);
        } catch (Exception e) {
            return null;
        }
        return response;
    }

//    @GetMapping("/profilehomepage")
//    public String profileHome(Model model, HttpSession session) {
//        if (!UtilsGeneric.isSessionActive(session)) {
//            return "error";
//        }
//
//        UtenteEntity utenteEntity = (UtenteEntity) session.getAttribute("utente");
//        model.addAttribute("utente", utenteEntity);
//        return "login/profilehomepage";
//    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpSession session) {
        try {
            session.invalidate();
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex);
        }
        return new ApiResponse(ApiResponseCodes.SUCCESS);
    }
}
