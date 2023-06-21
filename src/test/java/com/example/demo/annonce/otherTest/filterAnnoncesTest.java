package com.example.demo.annonce.otherTest;

import com.example.demo.annonce.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;

public class filterAnnoncesTest {
    @Test
    void testFilterAnnonces() {
        AnnonceService annonceServiceMock = Mockito.mock(AnnonceService.class);
        AnnonceController controller = new AnnonceController(annonceServiceMock);

        // Create a list of annonces for testing
        AnnonceDTO a1 = new AnnonceDTO("Title 1", "Description 1", 100.0, Type.IMMOBILIER);
        AnnonceDTO a2 = new AnnonceDTO("Title 2", "Description 2", 200.0, Type.VEHICULE);
        AnnonceDTO a3 = new AnnonceDTO("Title 3", "Description 3", 300.0, Type.EMPLOI);
        AnnonceDTO a4 = new AnnonceDTO("Title 4", "Description 4", 400.0, Type.IMMOBILIER);
        List<AnnonceDTO> annonces = List.of(a1, a2, a3, a4);


        // Test case 1: Filter by title (case-insensitive)
        // Test case 2: Filter by type/
//        // Test case 3: Filter by price range
//        // Test case 4: Filter by title and type
//        // Test case 5: Filter by title and price range
//        // Test case 6: Filter by type and price range
    }

}
