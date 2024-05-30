package com.unibg.UnibgProject.services.impl;

import com.unibg.UnibgProject.entity.UtenteEntity;
import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.repository.UtenteRepository;
import com.unibg.UnibgProject.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {
    @Autowired
    UtenteRepository utenteRepository;

    public Utente saveRegistrazione(Utente utente) throws Exception {
        UtenteEntity utenteEntity = new UtenteEntity();
        try{
            BeanUtils.copyProperties(utente,utenteEntity);
            utenteEntity = utenteRepository.save(utenteEntity);

            BeanUtils.copyProperties(utenteEntity,utente);
            return utente;

        } catch (DataIntegrityViolationException e) {
            if(utenteRepository.findByMail(utenteEntity.getMail()) !=null) {
                throw new DataIntegrityViolationException("Errore nella registrazione dell'utenza: mail già registrata.") ;
            } else {
                throw new Exception("Errore nella registrazione dell'utenza: contattare assistenza.");
            }
        }
    }

    public Utente login(Utente utente){
        UtenteEntity utenteEntity = utenteRepository.findByMailAndPsw(utente.getMail(), utente.getPsw());
        BeanUtils.copyProperties(utenteEntity,utente);
        return utente;
    }

    public Utente findByMail(String mail){
        Utente utente = new Utente();
        UtenteEntity utenteEntity = utenteRepository.findByMail(mail);
        BeanUtils.copyProperties(utenteEntity, utente);
        return utente;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loading user: "+ username);
        UserDetails user = (UserDetails)findByMail(username);
        return user;
    }
}
