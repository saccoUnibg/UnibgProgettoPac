package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;

public interface LoginService {

    Utente saveRegistrazione(Utente utente) throws Exception;

    Utente login(Utente utente);

    Utente findByMail(String mail);
}
