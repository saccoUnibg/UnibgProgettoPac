package com.unibg.UnibgProject.controller;

import com.unibg.UnibgProject.model.Utente;
import com.unibg.UnibgProject.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTests {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void loginTest(){
        log.info("Testing login post api");
        Utente utente = new Utente();
        utente.setMail("qwev");
        utente.setPsw("qwe");
        ResponseEntity<ApiResponse> response = template.postForEntity("/profilehomepage", utente, ApiResponse.class);
        assert response != null;
        log.info("login response: \n" + response.getBody());
        assert response.getStatusCode().is2xxSuccessful();
        response = template.postForEntity("/profilehomepage", utente, ApiResponse.class);
    }
}
