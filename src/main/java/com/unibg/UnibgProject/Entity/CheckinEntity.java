package com.unibg.UnibgProject.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity(name="check_in")
@Getter
@Setter
public class CheckinEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String mail;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String cf;
    @Column
    private String id_documento;
    @Column
    private String id_prenotazione;
}
