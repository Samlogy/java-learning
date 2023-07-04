package com.example.demo.annonce;

import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class annonceRepositoryTest {
    @Autowired
    private AnnonceRepository annonceRepository;

    private Annonce a1;
    private Annonce a2;
    private Annonce a3;

    @BeforeEach
    public void setUp() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        Annonce a1 = new Annonce(id1,"Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce a2 = new Annonce(id2,"Title 2", "description 2 ...", 200.0, Type.VEHICULE);
        Annonce a3 = new Annonce(id3,"Title 3", "description 3 ...", 500., Type.IMMOBILIER);
        annonceRepository.save(a1);
        annonceRepository.save(a2);
        annonceRepository.save(a3);
    }

    @AfterEach
    public void destroy() {
        annonceRepository.deleteAll();
    }

    @Test
    public void testGetAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        assertEquals(3, annonces.size());
        assertEquals(Type.EMPLOI, annonces.get(0).getType());
        assertEquals(Type.VEHICULE, annonces.get(1).getType());
        assertEquals(100.0, annonces.get(0).getPrice());
        assertEquals(200.0, annonces.get(1).getPrice());
    }

//    @Test
//    public void testFilterAnnonces() {
//        // title != null
//        List<Annonce> expected1 = annonceRepository.filterAnnonces("Title 1", null, null, null);
//        assertEquals(1, expected1.size());
//        assertEquals("Title 1", expected1.get(0).getTitle());
//
//        // type != null
//        List<Annonce> expected2 = annonceRepository.filterAnnonces(null, Type.IMMOBILIER, null, null);
//        assertEquals(1, expected2.size());
//        assertEquals("Title 3", expected2.get(0).getTitle());
//
//        // prineMin != null
//        List<Annonce> expected3 = annonceRepository.filterAnnonces(null, null, 100.0, null);
//        assertEquals(3, expected3.size());
//        assertEquals("Title 1", expected3.get(0).getTitle());
//
//        // priceMax != null
//        List<Annonce> expected4 = annonceRepository.filterAnnonces(null, null, null, 1000.0);
//        assertEquals(3, expected4.size());
//        assertEquals("Title 1", expected4.get(0).getTitle());
//    }

    @Test
    public void testGetAnnonceById() {
        UUID id1 = UUID.randomUUID();
        Annonce annonce = new Annonce(id1,"Title 1a", "description 1a ...", 100.0, Type.EMPLOI);
        annonceRepository.save(annonce);

        Optional<Annonce> result = annonceRepository.findById(id1);
        assertNotEquals(result, null);
    }

//    @Test
//    public void testGetInvalidAnnonce() {
//        UUID id = UUID.fromString("f9339c49-c40e-2317-aed4-95c916ea66ea");
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            annonceRepository.findById(id);
//        });
//        assertNotNull(exception);
//        assertEquals(exception.getClass(), NotFoundException.class);
//        assertEquals(exception.getMessage(), "Annonce not found with ID: " + id);
//    }

    @Test
    public void testGetCreateAnnonce() {
        Annonce saved = new Annonce("Title 0", "description 0 ...", 100.0, Type.EMPLOI);
        Annonce returned = annonceRepository.save(saved);
        assertNotNull(returned);
        assertEquals(saved.getTitle(), returned.getTitle());
    }

    @Test
    public void testDeleteAnnonce() {
        UUID id1 = UUID.randomUUID();
        Annonce a1 = new Annonce(id1,"Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        annonceRepository.deleteById(id1);
    }

    @Test
    public void testGetUpdateAnnonce() {
//        Annonce toUpdate = new Annonce("Title 0", "description 0 ...", 150.0, Type.VEHICULE);
//
//        Optional<Annonce> annonceExist = annonceRepository.findById(a1.getId());
//
//        annonceExist.
//
//        Annonce returned = annonceRepository.save(toUpdate);
//
//        assertNotNull(returned);
//        assertEquals(returned.getTitle(), returned.getTitle());

    }
}
