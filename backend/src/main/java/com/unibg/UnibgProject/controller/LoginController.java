package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.services.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/registrazioneform")
    public ResponseEntity<?> registrazioneForm(@RequestBody Utente utente) {
        try {
            utente = loginService.saveRegistrazione(utente);
            return ResponseEntity.status(HttpStatus.OK).body(utente);

        } catch (Exception e) {
            log.error("Error in registrazioneForm: ", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginForm(@RequestBody Utente utente, HttpSession session) {
        try {
            if (session.getAttribute("mail") == null) {
                log.info("login user " + utente.getMail());
                utente = loginService.login(utente);
                if (utente == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                } else {
                    session.setAttribute("mail", utente.getMail());
                }
            } else {
                utente = loginService.findByMail((String) session.getAttribute("mail"));
            }
            session.setAttribute("utente", utente);
            return ResponseEntity.status(HttpStatus.OK).body(utente);
        } catch (Exception e) {
            log.error("Error in loginForm: ", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IllegalStateException e) {
            log.error("Error in logout: ", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }
}
