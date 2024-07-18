package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.model.Volo;
import com.unibg.UnibgProject.utils.Coppia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoliControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    void givenNoScalo_whenVisualizzaVoli_ThenSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("mail");
        utenteTemp.setPsw("psw");

        HttpEntity<Utente> loginRequest = new HttpEntity<>(utenteTemp, headers);
        ResponseEntity<?> response = template.exchange("/login", HttpMethod.POST, loginRequest, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
        List<String> coockies = response.getHeaders().get("Set-Cookie");
        headers.put(HttpHeaders.COOKIE, coockies); // Necessario per avere la stessa Session ID

        HttpEntity<?> listaVoliRequest = new HttpEntity<>(null, headers);
        String urlTemplate = UriComponentsBuilder.fromPath("/voli/lista")
                .queryParam("partenza", "{partenza}")
                .queryParam("arrivo", "{arrivo}")
                .queryParam("data", "{data}")
                .queryParam("scalo", "{scalo}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("partenza", "Londra");
        params.put("arrivo", "Parigi");
        params.put("data", "2024-07-20");
        params.put("scalo", "false");
        response = template.exchange(urlTemplate, HttpMethod.GET, listaVoliRequest, new ParameterizedTypeReference<List<Volo>>() {}, params);
        assert response.getStatusCode().is2xxSuccessful();
        assert ((List<Volo>)response.getBody()).size() > 0;
    }

    @Test
    void givenScalo_whenVisualizzaVoli_ThenSuccess(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("mail");
        utenteTemp.setPsw("psw");

        HttpEntity<Utente> loginRequest = new HttpEntity<>(utenteTemp, headers);
        ResponseEntity<?> response = template.exchange("/login", HttpMethod.POST, loginRequest, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
        List<String> coockies = response.getHeaders().get("Set-Cookie");
        headers.put(HttpHeaders.COOKIE, coockies); // Necessario per avere la stessa Session ID

        HttpEntity<?> listaVoliRequest = new HttpEntity<>(null, headers);
        String urlTemplate = UriComponentsBuilder.fromPath("/voli/lista")
                .queryParam("partenza", "{partenza}")
                .queryParam("arrivo", "{arrivo}")
                .queryParam("data", "{data}")
                .queryParam("scalo", "{scalo}")
                .queryParam("scalo_min", "{scalo_min}")
                .queryParam("scalo_max", "{scalo_max}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("partenza", "Londra");
        params.put("arrivo", "Los Angeles");
        params.put("data", "2024-07-20");
        params.put("scalo", "true");
        params.put("scalo_min", "10");
        params.put("scalo_max", "30");
        response = template.exchange(urlTemplate, HttpMethod.GET, listaVoliRequest, new ParameterizedTypeReference<List<Coppia<Volo,Volo>>>() {}, params);
        assert response.getStatusCode().is2xxSuccessful();
        assert ((List<Coppia<Volo,Volo>>)response.getBody()).size() > 0;
    }

    @Test
    void whenCreaVolo_thenSuccess(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Volo voloToCreate = new Volo();
        voloToCreate.setPartenza("PartenzaTest");
        voloToCreate.setArrivo("ArrivoTest");
        voloToCreate.setData("2024-07-20");
        voloToCreate.setH_partenza("19:00:00");
        voloToCreate.setH_arrivo("21:00:00");
        voloToCreate.setPrezzo("50");
        voloToCreate.setCompagnia("UniBGAirline");

        HttpEntity<Volo> creaVoloRequest = new HttpEntity<>(voloToCreate, headers);
        ResponseEntity<?> response = template.exchange("/voli/crea", HttpMethod.POST, creaVoloRequest, Volo.class);
        assert response.getStatusCode().is2xxSuccessful();
    }
}
