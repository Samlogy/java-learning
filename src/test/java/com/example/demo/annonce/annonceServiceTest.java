package com.example.demo.annonce;

import com.example.demo.exception.NotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class annonceServiceTest {
    @Mock
    private AnnonceRepository annonceRepository;

    @InjectMocks
    private AnnonceService annonceService;

    private Annonce annonce;

    @BeforeEach
    public void setup() {
        UUID id = UUID.randomUUID();
        Annonce annonce = new Annonce(id, "Title 1", "description 1 ...", 100.0, Type.EMPLOI);
    }

    @Test
    public void testGetAnnonces() {
        Annonce a1 = new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce a2 = new Annonce("Title 2", "description 2 ...", 100.0, Type.EMPLOI);
        when(annonceRepository.findAll()).thenReturn(List.of(a1, a2));

        List<Annonce> annonceList = annonceService.getAnnonces();
        assertEquals(2, annonceList.size());
        assertEquals("Title 1", annonceList.get(0).getTitle());
        assertEquals("Title 2", annonceList.get(1).getTitle());
    }

    @Test
    public void testFilterAnnonces() {
        Annonce a1 = new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce a2 = new Annonce("Title 2", "description 2 ...", 200.0, Type.IMMOBILIER);
        Annonce a3 = new Annonce("Title 3", "description 3 ...", 100.0, Type.VEHICULE);
        List<Annonce> expected1 = List.of(a1);

        // title != null
        when(annonceRepository.filterAnnonces("Title 1", null, null, null)).thenReturn(expected1);

        List<Annonce> annonceList1 = annonceRepository.filterAnnonces("Title 1", null, null, null);
        assertEquals(1, annonceList1.size());
        assertEquals("Title 1", annonceList1.get(0).getTitle());

        // type != null
        List<Annonce> expected2 = List.of(a2);
        when(annonceRepository.filterAnnonces(null, Type.IMMOBILIER, null, null)).thenReturn(expected2);

        List<Annonce> annonceList2 = annonceService.filterAnnonces(null, null, null, Type.IMMOBILIER);
        assertEquals(1, annonceList2.size());
        assertEquals(Type.IMMOBILIER, annonceList2.get(0).getType());

        // priceMax != null
        List<Annonce> expected3 = List.of(a1, a2, a3);
        when(annonceRepository.filterAnnonces(null, null, null, 200.0)).thenReturn(expected3);

        List<Annonce> annonceList3 = annonceService.filterAnnonces(null, null, 200.0, null);
        assertEquals(3, annonceList3.size());

        // priceMin != null
        List<Annonce> expected4 = List.of(a2);
        when(annonceRepository.filterAnnonces(null, null, 150.0, null)).thenReturn(expected4);

        List<Annonce> annonceList4 = annonceService.filterAnnonces(null, 150.0, null, null);
        assertEquals(1, annonceList4.size());
    }

    @Test
    public void testGetAnnonceById() {
        when(annonceRepository.findById(annonce.getId())).thenReturn(Optional.of(annonce));
        Annonce result = annonceService.getAnnonceById(annonce.getId());
        assertNotEquals(result, null);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getType(), Type.EMPLOI);
    }

    @Test
    public void testGetInvalidAnnonceById() {
        when(annonceRepository.findById(annonce.getId())).thenThrow(new NotFoundException("Annonce not found with ID: " + annonce.getId()));
        Exception exception = assertThrows(NotFoundException.class, () -> {
            annonceService.getAnnonceById(annonce.getId());
        });
        assertTrue(exception.getMessage().contains("Annonce not found with ID: " + annonce.getId()));
    }

    @Test
    public void testCreateAnnonce() {
        annonceService.createAnnonce(annonce);
        verify(annonceRepository, times(1)).save(annonce);
        ArgumentCaptor<Annonce> annonceArgumentCaptor = ArgumentCaptor.forClass(Annonce.class);
        verify(annonceRepository).save(annonceArgumentCaptor.capture());
        Annonce annonceCreated = annonceArgumentCaptor.getValue();
        assertNotNull(annonceCreated.getId());
        assertEquals("Title 1", annonceCreated.getTitle());
    }

    @Test
    public void testDeleteAnnonce() {
        when(annonceRepository.findById(annonce.getId())).thenReturn(Optional.of(annonce));
        annonceService.deleteAnnonce(annonce.getId());
        verify(annonceRepository, times(1)).deleteById(annonce.getId());
        ArgumentCaptor<UUID> annonceArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(annonceRepository).deleteById(annonceArgumentCaptor.capture());
        UUID annonceIdDeleted = annonceArgumentCaptor.getValue();
        assertNotNull(annonceIdDeleted);
        assertEquals(annonce.getId(), annonceIdDeleted);
    }

    @Test
    public void testUpdateAnnonce() {
        Annonce annonceToUpdate = new Annonce("Title 2", "description 2 ...", 200.0, Type.VEHICULE);
        Annonce expected = new Annonce(annonce.getId(), "Title 2", "description 2 ...", 200.0, Type.VEHICULE, LocalDate.now());

        when(annonceService.updateAnnonce(annonce.getId(), annonceToUpdate)).thenReturn(expected);
        verify(annonceRepository, times(1)).save(annonceToUpdate);
        ArgumentCaptor<Annonce> annonceArgumentCaptor = ArgumentCaptor.forClass(Annonce.class);
        verify(annonceRepository).save(annonceArgumentCaptor.capture());
        Annonce annonceUpdated = annonceArgumentCaptor.getValue();
        assertNotNull(annonceUpdated.getId());
        assertEquals("Title 2", annonceUpdated.getTitle());
        assertEquals(Type.VEHICULE, annonceUpdated.getType());
    }
}
}