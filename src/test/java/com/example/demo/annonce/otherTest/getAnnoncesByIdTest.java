package com.example.demo.annonce.otherTest;

import com.example.demo.annonce.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class getAnnoncesByIdTest {
    @Test
    void testGetAnnonceById_ExistingId() {
        // Create an instance of the controller / service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Arrange
        UUID id = UUID.randomUUID();
        AnnonceDTO annonce = new AnnonceDTO(id, "Title", "Description", 100.0, Type.IMMOBILIER, LocalDate.now());

        // Mock behavior
        when(annonceServiceMock.getAnnonceById(id)).thenReturn(annonce);

        // Call the method being tested
        ResponseEntity<AnnonceDTO> response = controller.getAnnonceById(id);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(annonce, response.getBody());
    }

    @Test
    void testGetAnnonceById_NonExistingId() {
//        UUID id = UUID.randomUUID();
//        when(annonceRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<AnnonceDTO> result = annonceService.getAnnonceById(id);
//
//        // Assert
//        assertTrue(result.isEmpty());

        // Create an instance of the controller / service
//        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
//        AnnonceController controller = new AnnonceController(annonceServiceMock);
//        // Arrange
//        UUID id = UUID.randomUUID();
//        UUID nonExistingId =UUID.fromString("1d8c1ef0-299a-4d37-aad2-e0cf2b650965");
//
////         Set up the mock behavior
//        when(annonceServiceMock.getAnnonceById(id)).thenReturn(Optional.empty());
//
//        // Define the expected exception
//        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
//            controller.getAnnonceById(nonExistingId);
//        });
//
//        // Verify response (status code + exception message)
////        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertEquals("Annonce not found with ID: " + nonExistingId, exception.getMessage());

    }
}

