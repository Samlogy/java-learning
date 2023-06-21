package com.example.demo.units;

import com.example.demo.annonce.Annonce;
import com.example.demo.annonce.AnnonceRepository;
import com.example.demo.annonce.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class annonceRepositoryTest {
    @Autowired
    AnnonceRepository annonceRepository;

    @BeforeAll
    public void setUp() {
        annonceRepository.save(new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI));
        annonceRepository.save(new Annonce("Title 2", "description 2 ...", 200.0, Type.EMPLOI));
    }

    @Test
    public void testGetAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        assertEquals(2, annonces.size());
        assertEquals(Type.EMPLOI, annonces.get(0).getType());
        assertEquals(Type.EMPLOI, annonces.get(1).getType());
        assertEquals(100.0, annonces.get(0).getPrice());
        assertEquals(200.0, annonces.get(1).getPrice());
    }
}
