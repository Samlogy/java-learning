package com.example.demo.annonce;

import com.example.demo.annonce.AnnonceController;
import com.example.demo.annonce.AnnonceDTO;
import com.example.demo.annonce.Annonce;
import com.example.demo.annonce.AnnonceService;
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
//        List<Annonce> filteredByTitle = (List<Annonce>) controller.filterAnnonces("Title 1", 100.0, 200.0, Annonce.Type.IMMOBILIER);
//        List<AnnonceDTO> filteredByTitle = annonceServiceMock.filterAnnonces("Title 1");
//        Assertions.assertEquals(1, filteredByTitle.size());

        // Test case 2: Filter by type
        List<AnnonceDTO> filteredByType = annonceServiceMock.filterAnnonces("Title 1", 100.0, 150.0, Type.IMMOBILIER);
        Assertions.assertEquals(0, filteredByType.size());
//
//        // Test case 3: Filter by price range
//        List<Annonce> filteredByPrice = AnnonceFilter.filterAnnonces(annonces, null, null, 150.0, 350.0);
//        Assertions.assertEquals(2, filteredByPrice.size());
//
//        // Test case 4: Filter by title and type
//        List<Annonce> filteredByTitleAndType = AnnonceFilter.filterAnnonces(annonces, "title", Annonce.Type.IMMOBILIER);
//        Assertions.assertEquals(2, filteredByTitleAndType.size());
//
//        // Test case 5: Filter by title and price range
//        List<Annonce> filteredByTitleAndPrice = AnnonceFilter.filterAnnonces(annonces, "title", null, 150.0, 350.0);
//        Assertions.assertEquals(1, filteredByTitleAndPrice.size());
//
//        // Test case 6: Filter by type and price range
//        List<Annonce> filteredByTypeAndPrice = AnnonceFilter.filterAnnonces(annonces, null, Annonce.Type.VEHICULE, 150.0, 350.0);
//        Assertions.assertEquals(0, filteredByTypeAndPrice.size());
    }

}
