package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.entity.PrenotazioneEntity;
import com.unibg.UnibgProject.entity.VoloEntity;
import com.unibg.UnibgProject.model.Prenotazione;
import com.unibg.UnibgProject.model.Ricerca;

import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.repository.PrenotazioneRepository;
import com.unibg.UnibgProject.repository.VoliRepository;
import com.unibg.UnibgProject.services.VoliService;
import com.unibg.UnibgProject.utils.Coppia;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class VoliServiceImpl implements VoliService {

    @Autowired
    VoliRepository voliRepository;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Override
    public List<Volo> ricercaVoli(Ricerca ricerca) {

        List<VoloEntity> voliEntitylist = voliRepository.findByPartenzaAndArrivoIgnoreCaseAndData(ricerca.getPartenza(),ricerca.getArrivo(),ricerca.getData());
        List<Volo> voliList = new ArrayList<>();
        for(VoloEntity tempEntity: voliEntitylist){
           Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            // temp.setId(tempEntity.getId().toString());
            voliList.add(temp);
        }

        // Non voglio mostrare all'utente voli per cui ha gia' effettuato una prenotazione
        // 1. Ottengo la lista di id_volo dalle prenotazioni effettuate da una mail

        List<PrenotazioneEntity> prenotazioneEntityList = prenotazioneRepository.findByMail(ricerca.getMail());
        List<String> idVoliList = new ArrayList<>();
        for(PrenotazioneEntity temp: prenotazioneEntityList){
            idVoliList.add(temp.getIdVolo());
        }

        // 2. Controllo che l'id dei voli delle prenotazioni non sia presente nella lista dei voli generata dalla ricerca;
        //      in caso contrario devo rimuovere tale volo dalla lista di ricerca
        //      Inoltre, la lista dei voli e' gia' ordinata per id; quindi implementare un algoritmo di ricerca che trovi
        //      quelli gia' prenotati

        voliList.removeIf(volo -> idVoliList.contains(volo.getId()));

        return voliList;
    }

    public List<Volo> getVoliByIdList(List<Long> idList){
        List <VoloEntity> voloEntityList= voliRepository.findByIdIn(idList);
        List<Volo> voloList = new ArrayList<>();
        for (VoloEntity tempEntity: voloEntityList) {
            Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            // temp.setId(tempEntity.getId().toString());
            voloList.add(temp);
        }
        return voloList;
    }

    public List<Volo> getVoliByPrenotazioni(List<Prenotazione> listaPrenotazioni) {

        // 1. Ottengo una lista con gli id_volo delle prenotazioni;
        // 2. mi prendo gli oggetti "volo" il cui id è nella lista degli id_volo delle prenotazioni
        List<Long> idVoloList = new ArrayList<>();

        for (Prenotazione temp:listaPrenotazioni) {
            idVoloList.add(Long.valueOf(temp.getIdVolo()));
        }
        List <VoloEntity> voloEntityList= voliRepository.findByIdIn(idVoloList);

        List<Volo> voloList = new ArrayList<>();
        for (VoloEntity tempEntity: voloEntityList) {
            Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            // temp.setId(tempEntity.getId().toString());
            voloList.add(temp);
        }

        return voloList;
    }

    public Volo getVoloById(Long idVolo){
        VoloEntity voloEntity = voliRepository.findById(idVolo);
        Volo volo = new Volo();
        BeanUtils.copyProperties(voloEntity,volo);
        // volo.setId(voloEntity.getId().toString());
        return volo;
    }

    @Override
    public List<Coppia<Volo,Volo>> ricercaVoliScalo(Ricerca ricerca) {

        // Algoritmo di ricerca voli (vedi "resources/Utils/algoritmo.txt")

        // Voli da P a S
        List<VoloEntity> partenzaToScaloListEntity = voliRepository.findByPartenzaIgnoreCaseAndData(ricerca.getPartenza(),ricerca.getData());

        List<Volo> partenzaToScaloList = new ArrayList<>();
        // Conversione DTO
        for(VoloEntity tempEntity: partenzaToScaloListEntity){
            Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            partenzaToScaloList.add(temp);
        }

        // Lista possibili scali da P a S
        Set<String> scaliVolo1 = new HashSet<>();
        for(Volo temp: partenzaToScaloList){
            scaliVolo1.add(temp.getArrivo());
        }

        // Voli da S a P
        List<VoloEntity> scaloToArrivoListEntity = voliRepository.findByArrivoIgnoreCaseAndData(ricerca.getArrivo(),ricerca.getData());

        List<Volo> scaloToArrivoList = new ArrayList<>();
        //Conversione DTO
        for(VoloEntity tempEntity: scaloToArrivoListEntity){
            Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            scaloToArrivoList.add(temp);
        }

        // Lista possibili scali da S a A
        Set<String>scaliVolo2 = new HashSet<>();
        for(Volo temp: scaloToArrivoList){
            scaliVolo2.add(temp.getPartenza());
        }

        // Citta scalo possibili (intersezione tra due liste scali possibili)
        List<String> scali = scaliVolo1.stream()
                .filter(scaliVolo2::contains)
                .toList();

        // Filtro lista1 con "arrivo" che deve essere dentro lista "scali"

        partenzaToScaloList.removeIf(
                temp -> !scali.contains(temp.getArrivo())
        );

        scaloToArrivoList.removeIf(
                temp-> !scali.contains(temp.getPartenza())
        );

        // Abbiamo le due liste con i soli scali, dobbiamo fare una lista di coppie di voli

        List<Coppia<Volo,Volo>> listaCoppieVoli = new ArrayList<>();

        for(Volo temp1: partenzaToScaloList){
            for(Volo temp2: scaloToArrivoList){

                LocalTime time1 = LocalTime.parse(temp1.getH_arrivo());
                LocalTime time2 = LocalTime.parse(temp2.getH_partenza());
                Duration duration = Duration.between(time1,time2);
                long minutiDiScalo = duration.toMinutes();
                if(minutiDiScalo<0){
                    continue;
                }
                if( temp1.getArrivo().equalsIgnoreCase(temp2.getPartenza()) &&
                        minutiDiScalo>Integer.parseInt(ricerca.getScalo_min())&&
                        minutiDiScalo<Integer.parseInt(ricerca.getScalo_max())){
                            listaCoppieVoli.add(new Coppia<>(temp1,temp2));
                }
            }
        }

        return listaCoppieVoli;
    }

}
