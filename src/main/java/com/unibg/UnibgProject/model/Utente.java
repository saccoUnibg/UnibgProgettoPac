package com.unibg.UnibgProject.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonSerialize
public class Utente {

    private Long id;
    private String mail;
    private String psw;
    private String nome;
    private String cognome;
    private String cf;
    private String doc_id;

}
