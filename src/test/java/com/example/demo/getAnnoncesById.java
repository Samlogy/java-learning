package com.example.demo;

import com.example.demo.controllers.AnnonceController;
import com.example.demo.dto.AnnonceDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Annonce;
import com.example.demo.repository.AnnonceRepository;
import com.example.demo.service.AnnonceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class getAnnoncesById {
    @Test
    void testGetAnnonceById_ExistingId() {
        // Create an instance of the controller / service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Arrange
        UUID id = UUID.randomUUID();
        AnnonceDTO annonce = new AnnonceDTO(id, "Title", "Description", 100.0, Annonce.Type.IMMOBILIER, LocalDate.now());

        // Mock behavior
        when(annonceServiceMock.getAnnonceById(id)).thenReturn(Optional.of(annonce));

        // Call the method being tested
        ResponseEntity<Optional<AnnonceDTO>> response = controller.getAnnonceById(id);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Optional.of(annonce), response.getBody());
    }

    @Test
    void testGetAnnonceById_NonExistingId() {
        // Create an instance of the controller / service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);
        // Arrange
        UUID id = UUID.randomUUID();
        when(annonceServiceMock.getAnnonceById(id)).thenReturn(Optional.empty());

        // Call the method being tested
        ResponseEntity<Optional<AnnonceDTO>> response = controller.getAnnonceById(id);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Optional.empty(), response.getBody());
    }
}
