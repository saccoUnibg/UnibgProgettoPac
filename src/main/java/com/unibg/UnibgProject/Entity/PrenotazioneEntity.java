package com.unibg.UnibgProject.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table
@Getter
@Setter
@Entity(name="prenotazioni")
public class PrenotazioneEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String id_volo;
    @Column
    private String numero_biglietti;
    @Column
    private String mail;
    @Column
    private String spesa_totale;
}
