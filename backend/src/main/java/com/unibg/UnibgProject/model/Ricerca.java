package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Ricerca implements Serializable {

    private String partenza;
    private String arrivo;
    private String data;
    private String mail;
    private String scalo;
    private String scalo_min;
    private String scalo_max;

    /*

    scalo:      introduzione di un booleano per la scelta di inserire scali o meno nella ricerca
    scalo_min:  durata minima scalo
    scalo_max:  durata massima scalo

     */

    public Ricerca(String partenza,String arrivo, String data, String mail, String scalo, String scalo_min, String scalo_max){
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.data = data;
        this.scalo = scalo;
        this.scalo_min = scalo_min;
        this.scalo_max = scalo_max;
    }


}
