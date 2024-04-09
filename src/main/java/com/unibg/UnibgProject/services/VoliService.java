package com.unibg.UnibgProject.services;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;
import com.unibg.UnibgProject.model.Volo;

import java.util.List;

public interface VoliService {
     List<Volo> ricercaVoli(Ricerca ricerca);

     List<Volo> getVoliByIdList(List<Long> idList);
}
