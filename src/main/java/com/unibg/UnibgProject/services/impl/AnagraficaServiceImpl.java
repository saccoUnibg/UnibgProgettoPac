package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import com.unibg.UnibgProject.services.AnagraficaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UtenteEntity modificaAnagrafica(Utente utente) {
        try {
            UtenteEntity utenteEntity = utenteRepository.findByMail(utente.getMail());
            BeanUtils.copyProperties(utente, utenteEntity);
            utenteRepository.save(utenteEntity);
            return utenteEntity;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    @Override
    public Boolean eliminaAnagrafica(UtenteEntity utente) {
        try {
            utenteRepository.delete(utente);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
