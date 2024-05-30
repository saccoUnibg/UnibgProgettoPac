package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.LoginService;
import com.unibg.UnibgProject.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/login")
    public String login() {
        log.info("GET /login");
        return "login/login";
    }

    @GetMapping(value = "/registrazione")
    public String registrazioneUtente() {
        log.info("GET /registrazione");
        return "login/registrazione";
    }

    @PostMapping("/registrazioneform")
    public ApiResponse registrazioneForm(@RequestBody Utente utente) {
        ApiResponse response = new ApiResponse();
        try {
            utente = loginService.saveRegistrazione(utente);
            response.setObject(utente);

        } catch (Exception e) {
            response.setErrorMessage(e.toString());
        }
        return response;
    }

    @GetMapping(value = "/profilehomepage")
    public String homepage() {
        log.info("GET /profilehomepage");
        return "login/profilehomepage";
    }

    @PostMapping("/profilehomepage")
    public ApiResponse loginForm(@RequestBody Utente utente, HttpSession session) {
        log.info("POST /profilehomepage");
        ApiResponse response = new ApiResponse();
        try {
            if (session.getAttribute("mail") == null) {
                utente = loginService.login(utente);
                if (utente == null) {
                    log.warn("User not found");
                    return null;
                } else {
                    log.info("user {} found", utente.getMail());
                    session.setAttribute("mail", utente.getMail());
                }
            } else {
                utente = loginService.findByMail((String) session.getAttribute("mail"));
            }
            session.setAttribute("utente", utente);
            response.setObject(utente);
        } catch (Exception e) {
            response.setErrorMessage(e.toString());
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

    @RequestMapping("/logout")
    public ApiResponse logout(HttpSession session) {
        log.info("Request /logout");
        ApiResponse response = new ApiResponse();
        try {
            session.invalidate();
            response.setObject(null);
        } catch (IllegalStateException e) {
            response.setErrorMessage(e.toString());
        }
        return response;
    }
}
