package com.example.demo.annonce;

//public class annonceApiIntegrationTest {
//}

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
//import java.net.http.HttpHeaders;
import java.util.List;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class annonceApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private AnnonceService annonceService;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Sql(statements = "INSERT INTO annonce (id, title, description, price, type)" + "VALUES ('8b769ca9-89c4-4ff9-9ed4-9c9a6054fa68', 'Sample Title', 'Sample Description', 100.0, 'EMPLOI');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM annonce WHERE title='Sample Title'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAnnonces() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Annonce>> response = restTemplate.exchange(
                createURLWithPort(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
        List<Annonce> annonces = response.getBody();
        assert annonces != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(annonces.size(), annonceService.getAnnonces().size());
        assertEquals(annonces.size(), annonceRepository.findAll().size());
    }



    private String createURLWithPort() {
        return "http://localhost:" + port + "/api/annonce";
    }

}