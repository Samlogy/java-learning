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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;

public class deleteAnnonceById {
    @Test
    void testDeleteAnnonceById_NonExistingId() {
        // Create an instance of the controller / service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Arrange
        UUID id = UUID.randomUUID();
        AnnonceDTO annonce = new AnnonceDTO(id, "Title", "Description", 100.0, Type.IMMOBILIER, LocalDate.now());

        // Mock behavior
//        Mockito.when(annonceServiceMock.getAnnonceById(id)).thenReturn(existingAnnonceOptional);
//
//        // Call the method being tested
//        ResponseEntity<Optional<AnnonceDTO>> response = controller.getAnnonceById(id);
//
//        // Verify the response
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(Optional.of(annonce), response.getBody());
    }

    @Test
    void testDeleteAnnonceById_ExistingId() {
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Create some sample data
        UUID id = UUID.randomUUID();
        AnnonceDTO existingAnnonce = new AnnonceDTO(id, "Title", "Description", 100.0, Type.EMPLOI, LocalDate.now());

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.getAnnonceById(id)).thenReturn(existingAnnonce);

        // Call the method being tested
        ResponseEntity<Void> response = controller.deleteAnnonce(id);

        // Verify the deletion
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(annonceServiceMock).deleteAnnonce(id);
    }
}
