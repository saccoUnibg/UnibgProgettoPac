package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;

import com.unibg.UnibgProject.repository.VoliRepository;
import com.unibg.UnibgProject.services.VoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VoliServiceImpl implements VoliService {

    @Autowired
    VoliRepository voliRepository;

    @Override
    public List<VoloEntity> ricercaVoli(Ricerca ricerca) {
        return voliRepository.findByPartenzaAndArrivoIgnoreCaseAndData(ricerca.getPartenza(),ricerca.getArrivo(),ricerca.getData());
    }

}
