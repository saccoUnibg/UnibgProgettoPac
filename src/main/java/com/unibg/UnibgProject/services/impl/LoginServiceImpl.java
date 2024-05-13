package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import com.unibg.UnibgProject.services.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UtenteRepository utenteRepository;
    public UtenteEntity saveRegistrazione(Utente utente) throws Exception {
        UtenteEntity utenteEntity = new UtenteEntity();
        try{
            BeanUtils.copyProperties(utente,utenteEntity);
            utenteRepository.save(utenteEntity);
            return utenteEntity;

        } catch (DataIntegrityViolationException e) {
            if(utenteRepository.findByMail(utenteEntity.getMail()) !=null) {
                throw new DataIntegrityViolationException("Errore nella registrazione dell'utenza: mail già registrata.") ;
            } else {
                throw new Exception("Errore nella registrazione dell'utenza: contattare assistenza.");
            }
        }
    }

    public UtenteEntity login(Utente utente){
        UtenteEntity utenteEntity = new UtenteEntity();
        utenteEntity = utenteRepository.findByMailAndPsw(utente.getMail(), utente.getPsw());
        return utenteEntity;
    }

    public UtenteEntity findByMail(String mail){
        return utenteRepository.findByMail(mail);
    }
}
