package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Volo;

import java.util.List;

public interface PrenotazioneService {


    Prenotazione savePrenotazione(Prenotazione prenotazione);

    void saveCheckin(List<Checkin> checkin,String mail,String idPrenotazione);

    public List<Prenotazione> getVoliPrenotatiByMail(String mail);

    public void deletePrenotazione(String idPrenotazione);
}
