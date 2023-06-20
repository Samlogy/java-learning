package com.example.demo;

import com.example.demo.controllers.AnnonceController;
import com.example.demo.dto.AnnonceDTO;
import com.example.demo.model.Annonce;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.AnnonceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.internal.util.ExceptionHelper.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class deleteAnnonceById {
    @Test
    void testDeleteAnnonceById_NonExistingId() {
        // Create an instance of the controller / service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Arrange
        UUID id = UUID.randomUUID();
        AnnonceDTO annonce = new AnnonceDTO(id, "Title", "Description", 100.0, Annonce.Type.IMMOBILIER, LocalDate.now());

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
        AnnonceDTO existingAnnonce = new AnnonceDTO(id, "Title", "Description", 100.0, Annonce.Type.EMPLOI, LocalDate.now());
        Optional<AnnonceDTO> existingAnnonceOptional = Optional.of(existingAnnonce);

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.getAnnonceById(id)).thenReturn(existingAnnonceOptional);

        // Call the method being tested
        ResponseEntity<Void> response = controller.deleteAnnonce(id);

        // Verify the deletion
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(annonceServiceMock).deleteAnnonce(id);
    }
}
