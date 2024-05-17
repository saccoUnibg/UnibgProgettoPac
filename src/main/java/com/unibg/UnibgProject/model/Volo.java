package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Volo {

    private Long id;
    private String partenza;
    private String arrivo;
    private String data;
    private String compagnia;
    private String h_partenza;
    private String h_arrivo;
    private String prezzo;

}
