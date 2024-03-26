package com.unibg.UnibgProject.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class Ricerca {

    private String partenza;
    private String arrivo;
    private String data;
}
