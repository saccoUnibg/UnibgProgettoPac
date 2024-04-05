package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;

public interface PrenotazioneService {


    Long savePrenotazione(Prenotazione prenotazione);

    void saveCheckin(Checkin checkin);
}
