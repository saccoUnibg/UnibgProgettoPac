package com.unibg.UnibgProject.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    public String getHello(String nome,String cognome) {

        return "Hello ".concat(nome).concat(" ").concat(cognome);
    }
}
