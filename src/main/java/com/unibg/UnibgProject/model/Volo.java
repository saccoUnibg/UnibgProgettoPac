package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Volo {

    private String id;
    private String aeroporto_partenza;
    private String aeroporto_arrivo;
    private String data;
    private String compagnia;
    private String orario;
    private String prezzo;

}
