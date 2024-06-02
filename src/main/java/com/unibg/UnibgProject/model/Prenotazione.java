package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Prenotazione implements Serializable {

    private String id;
    private String idVolo;
    private String numero_biglietti;
    private String mail;
    private String spesa_totale;


}
