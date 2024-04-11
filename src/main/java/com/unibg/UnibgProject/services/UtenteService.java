package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;

public interface UtenteService {
    UtenteEntity modificaDati(Utente utente);

    Boolean eliminaUtente(UtenteEntity utente);
}
