package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;

public interface AnagraficaService {
    Utente modificaAnagrafica(Utente utente);

    Boolean eliminaAnagrafica(Utente utente);
}
