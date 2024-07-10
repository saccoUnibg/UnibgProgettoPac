package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnagraficaControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate template;

    @Test
    void whenModificaAnagrafica_thenSuccess(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("mail");
        utenteTemp.setPsw("psw");

        HttpEntity<Utente> request = new HttpEntity<>(utenteTemp, headers);
        ResponseEntity<?> response = template.exchange("/login", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();

        Utente loggedUser = (Utente) response.getBody();
        String oldName = loggedUser.getNome();
        log.info("User old name: {}",oldName);
        loggedUser.setNome("Paolo");
        log.info("Setting new name to: {}",loggedUser.getNome());

        List<String> coockies = response.getHeaders().get("Set-Cookie");
        headers.put(HttpHeaders.COOKIE, coockies); // Necessario per avere la stessa Session ID
        request = new HttpEntity<>(loggedUser, headers);
        response = template.exchange("/anagrafica/modifica", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();

        Utente modifiedUser= (Utente) response.getBody();
        assert modifiedUser.getNome().equals(loggedUser.getNome());

        //Rimetto il vecchio nome;
        loggedUser.setNome(oldName);
        request = new HttpEntity<>(loggedUser, headers);
        response = template.exchange("/anagrafica/modifica", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
        assert ((Utente) response.getBody()).getNome().equals(oldName);

    }

    //Il test di elimina è eseguito in LoginControllerTest


}
