package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Volo implements Serializable {

    private Long id;
    private String partenza;
    private String arrivo;
    private String data;
    private String compagnia;
    private String h_partenza;
    private String h_arrivo;
    private String prezzo;

}
