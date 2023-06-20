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

import java.util.List;

public class getAnnoncesTest {
    @Test
    void testGetAnnonces() {
        // Create a mock instance of the service
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);

        // Create some sample annonces
        AnnonceDTO a1 = new AnnonceDTO("Title 1", "description 1 ...", 100.0, Annonce.Type.EMPLOI);
        AnnonceDTO a2 = new AnnonceDTO("Title 2", "description 2 ...", 200.0, Annonce.Type.EMPLOI);
        List<AnnonceDTO> annonces = List.of(a1, a2);

        // Set up the mock behavior
        Mockito.when(annonceServiceMock.getAnnonces()).thenReturn(annonces);

        // Create an instance of the controller
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Call the method being tested
        ResponseEntity<List<AnnonceDTO>> response = controller.getAnnonces();

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(annonces, response.getBody());
    }
}


