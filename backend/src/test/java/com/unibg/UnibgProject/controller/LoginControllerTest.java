package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate template;

    @Test
    void givenExistUser_whenRegistrazioneForm_thenFail() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("mail");
        utenteTemp.setPsw("psw");

        HttpEntity<Utente> request = new HttpEntity<>(utenteTemp, headers);

        ResponseEntity<?> response = template.exchange("/registrazioneform", HttpMethod.POST, request, Object.class);

        assert response.getStatusCode().is4xxClientError();
    }

    @Test
    void givenNewUser_whenRegistrazioneForm_thenSuccessAndDeleteUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("new_mail");
        utenteTemp.setPsw("new_psw");

        HttpEntity<Utente> request = new HttpEntity<>(utenteTemp, headers);

        ResponseEntity<?> response = template.exchange("/registrazioneform", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
        Utente newUtente = (Utente) response.getBody();

        response = template.exchange("/login", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();

        List<String> coockies = response.getHeaders().get("Set-Cookie");
        headers.put(HttpHeaders.COOKIE, coockies); // Necessario per avere la stessa Session ID
        request = new HttpEntity<>(newUtente, headers);

        response = template.exchange("/anagrafica/elimina", HttpMethod.POST, request, String.class);
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    void givenCorrectUser_whenLogin_thenLoginSuccess(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("mail");
        utenteTemp.setPsw("psw");

        HttpEntity<Utente> request = new HttpEntity<>(utenteTemp, headers);

        ResponseEntity<?> response = template.exchange("/login", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    void givenWrongUser_whenLogin_thenLoginFail(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        Utente utenteTemp = new Utente();
        utenteTemp.setMail("sdfsfsdf");
        utenteTemp.setPsw("new_sdfsfssdfpsw");

        HttpEntity<Utente> request = new HttpEntity<>(utenteTemp, headers);

        ResponseEntity<?> response = template.exchange("/login", HttpMethod.POST, request, Utente.class);
        assert response.getStatusCode().is4xxClientError();
    }

    @Test
    void when_Logout_thenSuccess(){
        ResponseEntity<?> response = template.exchange("/logout", HttpMethod.POST, null, String.class);
        assert response.getStatusCode().is2xxSuccessful();
    }

}
