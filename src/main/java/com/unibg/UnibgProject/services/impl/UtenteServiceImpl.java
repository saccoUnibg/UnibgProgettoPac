package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import com.unibg.UnibgProject.services.UtenteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UtenteEntity modificaDati(Utente utente) {
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
    public Boolean eliminaUtente(UtenteEntity utente) {
        try {
            utenteRepository.delete(utente);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
