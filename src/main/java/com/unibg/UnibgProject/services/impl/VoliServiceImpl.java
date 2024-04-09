package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Ricerca;

import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.repository.VoliRepository;
import com.unibg.UnibgProject.services.VoliService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VoliServiceImpl implements VoliService {

    @Autowired
    VoliRepository voliRepository;

    @Override
    public List<Volo> ricercaVoli(Ricerca ricerca) {
        List<VoloEntity> voliEntitylist = voliRepository.findByPartenzaAndArrivoIgnoreCaseAndData(ricerca.getPartenza(),ricerca.getArrivo(),ricerca.getData());
        List<Volo> voliList = new ArrayList<>();
        for(VoloEntity tempEntity: voliEntitylist){
           Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            temp.setId(tempEntity.getId().toString());
            voliList.add(temp);
        }
        return voliList;
    }

    public List<Volo> getVoliByIdList(List<Long> idList){
        List <VoloEntity> voloEntityList= voliRepository.findByIdIn(idList);
        List<Volo> voloList = new ArrayList<>();
        for (VoloEntity tempEntity: voloEntityList) {
            Volo temp = new Volo();
            BeanUtils.copyProperties(tempEntity,temp);
            temp.setId(tempEntity.getId().toString());
            voloList.add(temp);
        }
        return voloList;
    }

}
