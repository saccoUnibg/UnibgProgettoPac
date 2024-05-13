package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.entity.PrenotazioneEntity;
import com.unibg.UnibgProject.entity.VoloEntity;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;

import java.util.List;

public interface VoliService {

     List<Volo> ricercaVoli(Ricerca ricerca);

     List<Volo> getVoliByIdList(List<Long> idList);

     List<Volo> getVoliByPrenotazioni(List<Prenotazione> prenotazioneList);

     Volo getVoloById(Long id);
}
