package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.*;
import com.unibg.UnibgProject.utils.Coppia;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrenotazioneControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void whenCreaPrenotazione_thenSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Volo volo = new Volo();
        volo.setId(100L);

        HttpEntity<Volo> request = new HttpEntity<>(volo, headers);
        ResponseEntity<?> response = template.exchange("/prenotazioni/crea", HttpMethod.POST, request, String.class);
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    void givenPrenotazione_whenCheckIn_ThenSuccessAndDeletePrenotazione() {
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

        Volo volo = new Volo();
        volo.setId(100L);

        HttpEntity<Volo> prenotazioneRequest = new HttpEntity<>(volo, headers);
        response = template.exchange("/prenotazioni/crea", HttpMethod.POST, prenotazioneRequest, String.class);
        assert response.getStatusCode().is2xxSuccessful();

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setNumero_biglietti("5");
        prenotazione.setSpesa_totale("50");

        HttpEntity<Prenotazione> checkinRequest = new HttpEntity<>(prenotazione, headers);
        response = template.exchange("/prenotazioni/check-in", HttpMethod.POST, checkinRequest, CheckinList.class);
        assert response.getStatusCode().is2xxSuccessful();

        HttpEntity<?> visualizzaRequest = new HttpEntity<>(null, headers);
        response = template.exchange("/prenotazioni/visualizza", HttpMethod.GET, visualizzaRequest, new ParameterizedTypeReference<List<Volo>>() {});
        assert response.getStatusCode().is2xxSuccessful();

        HttpEntity<Volo> eliminaRequest = new HttpEntity<>(volo, headers);
        response = template.exchange("/prenotazioni/elimina", HttpMethod.POST, eliminaRequest, String.class);
        assert response.getStatusCode().is2xxSuccessful();

        response = template.exchange("/prenotazioni/elimina/conferma", HttpMethod.POST, eliminaRequest, String.class);
        assert response.getStatusCode().is2xxSuccessful();

    }

    @Test
    void givenPrenotazione_whenCheckIn_ThenSuccessAndSaveCheckIn() {
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

        Volo volo = new Volo();
        volo.setId(100L);

        HttpEntity<Volo> prenotazioneRequest = new HttpEntity<>(volo, headers);
        response = template.exchange("/prenotazioni/crea", HttpMethod.POST, prenotazioneRequest, String.class);
        assert response.getStatusCode().is2xxSuccessful();

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setNumero_biglietti("2");
        prenotazione.setSpesa_totale("50");

        HttpEntity<Prenotazione> checkinRequest = new HttpEntity<>(prenotazione, headers);
        response = template.exchange("/prenotazioni/check-in", HttpMethod.POST, checkinRequest, CheckinList.class);
        assert response.getStatusCode().is2xxSuccessful();

        CheckinList checkinList = (CheckinList) response.getBody();
        List<Checkin> checkins = checkinList.getCheckinList();
        for (int i = 0; i < checkinList.getCheckinList().size(); i++) {
            checkins.get(i).setNome("nome" + i);
            checkins.get(i).setCognome("cognome" + i);
        }

        HttpEntity<?> saveCheckInRequest = new HttpEntity<>(checkinList, headers);
        response = template.exchange("/prenotazioni/success", HttpMethod.POST, saveCheckInRequest, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    void givenPrenotazioneScalo_whenCheckIn_ThenSuccessAndSaveCheckIn()
    {
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

        Volo primoVolo = new Volo();
        primoVolo.setId(143l);
        Volo secondoVolo = new Volo();
        secondoVolo.setId(140l);
        Coppia<Volo,Volo> coppiaVoli = new Coppia<>(primoVolo,secondoVolo);

        HttpEntity<Coppia<Volo,Volo>> prenotazioneRequest = new HttpEntity<>(coppiaVoli, headers);
        response = template.exchange("/prenotazioniScalo/crea", HttpMethod.POST, prenotazioneRequest, String.class);
        assert response.getStatusCode().is2xxSuccessful();

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setNumero_biglietti("2");
        prenotazione.setSpesa_totale("50");

        HttpEntity<Prenotazione> checkinRequest = new HttpEntity<>(prenotazione, headers);
        response = template.exchange("/prenotazioniScalo/check-in", HttpMethod.POST, checkinRequest, CheckinList.class);
        assert response.getStatusCode().is2xxSuccessful();

        CheckinList checkinList = (CheckinList) response.getBody();
        List<Checkin> checkins = checkinList.getCheckinList();
        for (int i = 0; i < checkinList.getCheckinList().size(); i++) {
            checkins.get(i).setNome("nomeTEST" + i);
            checkins.get(i).setCognome("cognomeTEST" + i);
        }

        HttpEntity<?> saveCheckInRequest = new HttpEntity<>(checkinList, headers);
        response = template.exchange("/prenotazioniScalo/success", HttpMethod.POST, saveCheckInRequest, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
    }
}
