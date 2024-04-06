package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.CheckinEntity;
import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.repository.CheckinRepository;
import com.unibg.UnibgProject.repository.PrenotazioneRepository;
import com.unibg.UnibgProject.services.LoginService;
import com.unibg.UnibgProject.services.PrenotazioneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    CheckinRepository checkinRepository;

    @Override
    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        PrenotazioneEntity prenotazioneEntity = new PrenotazioneEntity();
        BeanUtils.copyProperties(prenotazione,prenotazioneEntity);
        prenotazioneEntity = prenotazioneRepository.save(prenotazioneEntity);
        BeanUtils.copyProperties(prenotazioneEntity,prenotazione);
        return prenotazione;
    }

    @Override
    public void saveCheckin(List<Checkin> checkinList,String mail,String idPrenotazione) {
        List<CheckinEntity> checkinEntityList = new ArrayList<>();
        for(Checkin temp: checkinList){
            CheckinEntity checkinEntity = new CheckinEntity();
            BeanUtils.copyProperties(temp,checkinEntity);
            checkinEntity.setMail(mail);
            checkinEntity.setId_prenotazione(idPrenotazione);
            checkinEntityList.add(checkinEntity);
        }
        checkinRepository.saveAll(checkinEntityList);
    }
}
