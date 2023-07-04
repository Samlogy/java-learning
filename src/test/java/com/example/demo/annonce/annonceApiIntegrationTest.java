package com.example.demo.annonce;

//public class annonceApiIntegrationTest {
//}

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

//    @Test
//    @Sql(statements = "INSERT INTO annonce (id, title, description, price, type)" + "VALUES ('8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa', 'Sample Title', 'Sample Description', 100.0, 'EMPLOI');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "DELETE FROM annonce WHERE id='8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void testGetAnnonces() {
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//        ResponseEntity<List<Annonce>> response = restTemplate.exchange(
//                createURLWithPort(""), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
//        List<Annonce> annonces = response.getBody();
//        assert annonces != null;
//        assertEquals(response.getStatusCodeValue(), 200);
//        assertEquals(annonces.size(), annonceService.getAnnonces().size());
//        assertEquals(annonces.size(), annonceRepository.findAll().size());
//    }

//    @Test
//    @Sql(statements = "INSERT INTO annonce (id, title, description, price, type)" + "VALUES ('8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa', 'Title 1', 'Sample Description', 100.0, 'EMPLOI');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "DELETE FROM annonce WHERE id='8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void testFilterAnnonces() {
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//
//        // title != null
//        ResponseEntity<List<Annonce>> response1 = restTemplate.exchange(
//                createURLWithPort("/filter?title=Title 1") , HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
//        List<Annonce> expected1 = response1.getBody();
//        assert expected1 != null;
//        assertEquals(response1.getStatusCodeValue(), 200);
//        assertEquals(expected1.size(), annonceService.filterAnnonces("Title 1", null, null, null).size());
//        assertEquals(expected1.size(), annonceRepository.filterAnnonces("Title 1", null, null, null).size());
//
//        // type != null
//        ResponseEntity<List<Annonce>> response2 = restTemplate.exchange(
//                createURLWithPort("/filter?type=EMPLOI"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
//        List<Annonce> expected2 = response2.getBody();
//        assert expected2 != null;
//        assertEquals(response2.getStatusCodeValue(), 200); // 6
//        assertEquals(expected2.size(), annonceService.filterAnnonces(null, null, null, Type.EMPLOI).size());
//        assertEquals(expected2.size(), annonceRepository.filterAnnonces(null, Type.EMPLOI, null, null).size());
//
//        // priceMin != null
//        ResponseEntity<List<Annonce>> response3 = restTemplate.exchange(
//                createURLWithPort("/filter?priceMin=100") , HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
//        List<Annonce> expected3 = response3.getBody();
//        assert expected3 != null;
//        assertEquals(response3.getStatusCodeValue(), 200); // 6
//        assertEquals(expected3.size(), annonceService.filterAnnonces(null, 100.0, null, null).size());
//        assertEquals(expected3.size(), annonceRepository.filterAnnonces(null, null, 100.0, null).size());
//
//        // priceMax != null
//        ResponseEntity<List<Annonce>> response4 = restTemplate.exchange(
//                createURLWithPort("/filter?priceMax=100000") , HttpMethod.GET, entity, new ParameterizedTypeReference<List<Annonce>>(){});
//        List<Annonce> expected4 = response4.getBody();
//        assert expected4 != null;
//        assertEquals(response4.getStatusCodeValue(), 200); // 7
//        assertEquals(expected4.size(), annonceService.filterAnnonces(null, null, 100000.0, null).size());
//        assertEquals(expected4.size(), annonceRepository.filterAnnonces(null, null, null, 100000.0).size());
//    }

    @Test
    @Sql(statements = "DELETE FROM annonce WHERE id='8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreateAnnonce() throws JsonProcessingException {
        UUID id = UUID.fromString("8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa");
        Annonce annonce = new Annonce(id, "Title 0", "description 0 ...", 150.0, Type.VEHICULE);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(annonce), headers);
        ResponseEntity<Annonce> response = restTemplate.exchange(
                createURLWithPort(""), HttpMethod.POST, entity, Annonce.class);
        assertEquals(response.getStatusCodeValue(), 201);
        Annonce result = Objects.requireNonNull(response.getBody());
        assertEquals(result.getTitle(), "Title 0");
        assertEquals(result.getType(), annonceRepository.save(annonce).getType());
    }

    @Test
    @Sql(statements = "INSERT INTO annonce (id, title, description, price, type)" + "VALUES ('8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa', 'Title 1', 'Sample Description', 100.0, 'EMPLOI');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM annonce WHERE id='8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteAnnonce() {
        UUID id = UUID.fromString("8b769ca9-89c4-4ff9-9ed4-9c9a6054faaa");
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/"+id), HttpMethod.DELETE, null, String.class);
        String annocneRes = response.getBody();
        assertEquals(response.getStatusCodeValue(), 204);
        assertEquals(annocneRes, null);
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + "/api/annonce" + path;
    }

}