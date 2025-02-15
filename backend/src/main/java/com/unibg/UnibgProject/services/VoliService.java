package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.utils.Coppia;

import java.util.List;

public interface VoliService {

     Volo creaVolo(Volo voloDaCreare);

     List<Volo> ricercaVoli(Ricerca ricerca);

     List<Volo> getVoliByIdList(List<Long> idList);

     List<Volo> getVoliByPrenotazioni(List<Prenotazione> prenotazioneList);

     Volo getVoloById(Long id);

     List<Coppia<Volo,Volo>> ricercaVoliScalo(Ricerca ricerca);
}
