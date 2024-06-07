package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Utente implements Serializable {

    private Long id;
    private String mail;
    private String psw;
    private String nome;
    private String cognome;
    private String cf;
    private String doc_id;

}
