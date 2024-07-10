package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import com.unibg.UnibgProject.services.AnagraficaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnagraficaServiceImpl implements AnagraficaService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public Utente modificaAnagrafica(Utente utente) {
        try {
            UtenteEntity utenteEntity = utenteRepository.findByMail(utente.getMail());
            BeanUtils.copyProperties(utente, utenteEntity);
            utenteEntity = utenteRepository.save(utenteEntity);
            BeanUtils.copyProperties(utenteEntity, utente);
            return utente;
        } catch (Exception ex) {
            log.error("Error: ", ex);
        }
        return null;
    }

    @Override
    public Boolean eliminaAnagrafica(Utente utente) {
        try {
            UtenteEntity utenteEntity = utenteRepository.findByMail(utente.getMail());
            utenteRepository.delete(utenteEntity);
            log.info("Utente eliminato con successo");
            return true;
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return false;
        }
    }
}
