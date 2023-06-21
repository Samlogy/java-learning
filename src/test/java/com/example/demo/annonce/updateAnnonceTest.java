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

public class updateAnnonceTest {
    @Test
    void testUpdateAnnonceById_ExistingId() {
        // Create a mock instance of the service / controller
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Create some sample annonces
        UUID id = UUID.randomUUID();
        AnnonceDTO annonceUpdated = new AnnonceDTO(id, "Title", "Description", 100.0, Annonce.Type.IMMOBILIER, LocalDate.now());
        AnnonceDTO annonceToUpdate = new AnnonceDTO("Title", "Description", 100.0, Annonce.Type.IMMOBILIER);

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.updateAnnonce(id, annonceToUpdate)).thenReturn(annonceUpdated);

        // Call the method being tested
        ResponseEntity<AnnonceDTO> response = controller.updateAnnonce(id, annonceToUpdate);

        // Verify the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(annonceUpdated, response.getBody());
    }

    @Test
    void testUpdateAnnonceById_NonExistingId() {
    }
}
