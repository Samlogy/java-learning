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
}
