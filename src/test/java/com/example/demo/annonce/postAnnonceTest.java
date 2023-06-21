package com.example.demo.annonce;

import com.example.demo.annonce.AnnonceController;
import com.example.demo.annonce.AnnonceDTO;
import com.example.demo.annonce.Annonce;
import com.example.demo.annonce.AnnonceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

public class postAnnonceTest {
    @Test
    void testPostAnnonce() {
        // Create a mock instance of the service / controller
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Create some sample annonces
        UUID id = UUID.randomUUID();
        AnnonceDTO annonceToPost = new AnnonceDTO("Title", "Description", 100.0, Type.IMMOBILIER);
        AnnonceDTO annoncePosted = new AnnonceDTO(id, "Title", "Description", 100.0, Type.IMMOBILIER, LocalDate.now());

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.createAnnonce(annonceToPost)).thenReturn(annoncePosted);

        // Call the method being tested
        ResponseEntity<AnnonceDTO> response = controller.createAnnonce(annonceToPost);

        // Verify the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(annoncePosted, response.getBody());
    }
}
