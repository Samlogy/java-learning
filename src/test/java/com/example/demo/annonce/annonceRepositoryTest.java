package com.example.demo.annonce;

import com.example.demo.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class annonceRepositoryTest {
    @Autowired
    AnnonceRepository annonceRepository;

    @Test
    public void testGetAnnonces() {
        annonceRepository.save(new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI));
        annonceRepository.save(new Annonce("Title 2", "description 2 ...", 200.0, Type.EMPLOI));

        List<Annonce> annonces = annonceRepository.findAll();
        assertEquals(2, annonces.size());
        assertEquals(Type.EMPLOI, annonces.get(0).getType());
        assertEquals(Type.EMPLOI, annonces.get(1).getType());
        assertEquals(100.0, annonces.get(0).getPrice());
        assertEquals(200.0, annonces.get(1).getPrice());
    }

    @Test
    public void testFilterAnnonces() {
        annonceRepository.save(new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI));
        annonceRepository.save(new Annonce("Title 2", "description 2 ...", 200.0, Type.IMMOBILIER));
        annonceRepository.save(new Annonce("Title 3", "description 3 ...", 500., Type.VEHICULE));

        // title != null
        List<Annonce> expected1 = annonceRepository.filterAnnonces("Title 1", null, null, null);
        assertEquals(1, expected1.size());
        assertEquals("Title 1", expected1.get(0).getTitle());

        // type != null
        List<Annonce> expected2 = annonceRepository.filterAnnonces(null, Type.IMMOBILIER, null, null);
        assertEquals(1, expected2.size());
        assertEquals("Title 2", expected2.get(0).getTitle());

        // prineMin != null
        List<Annonce> expected3 = annonceRepository.filterAnnonces(null, null, 100.0, null);
        assertEquals(3, expected3.size());
        assertEquals("Title 1", expected3.get(0).getTitle());

        // priceMax != null
        List<Annonce> expected4 = annonceRepository.filterAnnonces(null, null, null, 1000.0);
        assertEquals(3, expected4.size());
        assertEquals("Title 1", expected4.get(0).getTitle());
    }
}
