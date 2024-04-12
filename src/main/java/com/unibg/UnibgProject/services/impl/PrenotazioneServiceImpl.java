package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.CheckinEntity;
import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import com.unibg.UnibgProject.model.Checkin;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.repository.CheckinRepository;
import com.unibg.UnibgProject.repository.PrenotazioneRepository;
import com.unibg.UnibgProject.services.PrenotazioneService;
import com.unibg.UnibgProject.services.VoliService;
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

    @Autowired
    VoliService voliService;

    @Override
    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        PrenotazioneEntity prenotazioneEntity = new PrenotazioneEntity();
        BeanUtils.copyProperties(prenotazione, prenotazioneEntity);
        prenotazioneEntity = prenotazioneRepository.save(prenotazioneEntity);
        BeanUtils.copyProperties(prenotazioneEntity, prenotazione);
        prenotazione.setId(prenotazioneEntity.getId().toString());
        return prenotazione;
    }

    @Override
    public void saveCheckin(List<Checkin> checkinList, String mail, String idPrenotazione) {
        List<CheckinEntity> checkinEntityList = new ArrayList<>();
        for (Checkin temp : checkinList) {
            CheckinEntity checkinEntity = new CheckinEntity();
            BeanUtils.copyProperties(temp, checkinEntity);
            checkinEntity.setMail(mail);
            checkinEntity.setIdPrenotazione(idPrenotazione);
            checkinEntityList.add(checkinEntity);
        }
        checkinRepository.saveAll(checkinEntityList);
    }

    public List<Prenotazione> getVoliPrenotatiByMail(String mail) {

        // recupero le informazioni in base alla mail dell'utenza
        List<PrenotazioneEntity> prenotazioneEntityList = prenotazioneRepository.findByMail(mail);

        //Per recuperare le informazioni dei voli relativi alle prenotazioni, cerco i voli con id_volo
        List<Prenotazione> prenotazioneList = new ArrayList<>();
        for (PrenotazioneEntity tempEntity : prenotazioneEntityList) {
            Prenotazione temp = new Prenotazione();
            BeanUtils.copyProperties(tempEntity, temp);
            temp.setId(tempEntity.getId().toString());
            prenotazioneList.add(temp);
        }
        return prenotazioneList;
    }

}
