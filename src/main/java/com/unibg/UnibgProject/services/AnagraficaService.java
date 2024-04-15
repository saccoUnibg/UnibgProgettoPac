package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;

public interface AnagraficaService {
    UtenteEntity modificaAnagrafica(Utente utente);

    Boolean eliminaAnagrafica(UtenteEntity utente);
}
