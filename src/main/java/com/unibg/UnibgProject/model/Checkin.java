package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Checkin implements Serializable {

    private String mail;
    private String nome;
    private String cognome;
    private String cf;
    private String id_documento;
    private String idPrenotazione;

}
