package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnagraficaControllerTests {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void modificaAnagraficaGetTest() {
        log.info("Testing modificaAnagrafica get api");
        ResponseEntity<String> response = template.getForEntity("/anagrafica/modifica", String.class);
        assert response != null;
        log.info("modificaAnagrafica response: \n" + response.getBody());
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    public void modificaAnagraficaPostTestWithoutSession() {
        log.info("Testing modificaAnagrafica post api");
        Utente utente = new Utente();
        ResponseEntity<String> response = template.postForEntity("/anagrafica/modifica/success", utente, String.class);
        assert response != null;
        log.info("modificaAnagrafica response: \n" + response.getBody());
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    public void modificaAnagraficaPostTestWithSession() {
        log.info("Testing modificaAnagrafica post api");

        Utente utente = new Utente();
        ResponseEntity<String> response = template.postForEntity("/anagrafica/modifica/success", utente, String.class);
        assert response != null;
        log.info("modificaAnagrafica response: \n" + response.getBody());
        assert response.getStatusCode().is2xxSuccessful();
    }
}
