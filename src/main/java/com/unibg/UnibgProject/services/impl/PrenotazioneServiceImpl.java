package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.CheckinEntity;
import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.repository.PrenotazioneRepository;
import com.unibg.UnibgProject.services.PrenotazioneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Override
    public void savePrenotazione(Prenotazione prenotazione) {
        PrenotazioneEntity prenotazioneEntity = new PrenotazioneEntity();
        BeanUtils.copyProperties(prenotazione,prenotazioneEntity);
        prenotazioneRepository.save(prenotazioneEntity);
    }

    @Override
    public void saveCheckin(Checkin checkin) {
        CheckinEntity checkinEntity = new CheckinEntity();
        BeanUtils.copyProperties(checkin,checkinEntity);

    }
}
