package com.example.demo;

import com.example.demo.controllers.AnnonceController;
import com.example.demo.dto.AnnonceDTO;
import com.example.demo.model.Annonce;
import com.example.demo.service.AnnonceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class postAnnonceTest {
    @Test
    void testPostAnnonce() {
        // Create a mock instance of the service / controller
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Create some sample annonces
        UUID id = UUID.randomUUID();
        Annonce annonceToPost = new Annonce("Title", "Description", 100.0, Annonce.Type.IMMOBILIER);
        AnnonceDTO annoncePosted = new AnnonceDTO(id, "Title", "Description", 100.0, Annonce.Type.IMMOBILIER, LocalDate.now());

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.createAnnonce(annonceToPost)).thenReturn(annoncePosted);

        // Call the method being tested
        ResponseEntity<AnnonceDTO> response = controller.createAnnonce(annonceToPost);

        // Verify the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(annoncePosted, response.getBody());
    }
}
