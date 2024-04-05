package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;

public interface LoginService {

    UtenteEntity saveRegistrazione(Utente utente) throws Exception;
    UtenteEntity login(Utente utente);

    UtenteEntity findByMail(String mail);
}
