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
        UUID id = UUID.randomUUID();
        Annonce annonce = new Annonce(id, "Title 1", "description 1 ...", 100.0, Type.EMPLOI);

        when(annonceRepository.findById(id)).thenReturn(Optional.of(annonce));
        Annonce result = annonceService.getAnnonceById(id);
        assertNotEquals(result, null);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getType(), Type.EMPLOI);
    }

    @Test
    public void testGetInvalidAnnonceById() {
        UUID id = UUID.fromString("1ab3fab0-eafa-44b2-ae42-1be693705c39");

        when(annonceRepository.findById(id)).thenThrow(new NotFoundException("Annonce not found with ID: " + id));
        Exception exception = assertThrows(NotFoundException.class, () -> {
            annonceService.getAnnonceById(id);
        });
        assertTrue(exception.getMessage().contains("Annonce not found with ID: " + id));
    }

    @Test
    public void testCreateAnnonce() {
        UUID id = UUID.randomUUID();
        Annonce annonce = new Annonce(id, "Title 1", "description 1 ...", 100.0, Type.EMPLOI);

        annonceService.createAnnonce(annonce);
        verify(annonceRepository, times(1)).save(annonce);
        ArgumentCaptor<Annonce> annonceArgumentCaptor = ArgumentCaptor.forClass(Annonce.class);
        verify(annonceRepository).save(annonceArgumentCaptor.capture());
        Annonce annonceCreated = annonceArgumentCaptor.getValue();
        assertEquals("Title 1", annonceCreated.getTitle());
    }

    @Test
    public void testDeleteAnnonce() {
        UUID id = UUID.randomUUID();
        Annonce annonce = new Annonce(id, "Title 1", "description 1 ...", 100.0, Type.EMPLOI);

        when(annonceRepository.findById(annonce.getId())).thenReturn(Optional.of(annonce));
        annonceService.deleteAnnonce(annonce.getId());
        verify(annonceRepository, times(1)).deleteById(annonce.getId());
        ArgumentCaptor<UUID> annonceArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(annonceRepository).deleteById(annonceArgumentCaptor.capture());
        UUID annonceIdDeleted = annonceArgumentCaptor.getValue();
        assertNull(annonceIdDeleted);
        assertEquals(annonce.getId(), annonceIdDeleted);
    }

    @Test
    public void testUpdateAnnonce() {
        UUID id = UUID.randomUUID();
        Annonce annonce = new Annonce(id, "Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce toUpdate = new Annonce("Title 2", "description 2 ...", 200.0, Type.VEHICULE);

        when(annonceRepository.findById(id)).thenReturn(Optional.of(annonce));
        Annonce annonceToUpdate = annonceService.getAnnonceById(id); // annonceToUpdate

        annonceToUpdate.setTitle(annonceToUpdate.getTitle());
        annonceToUpdate.setType(annonceToUpdate.getType());
        annonceToUpdate.setDescription(annonceToUpdate.getDescription());
        annonceToUpdate.setPrice(annonceToUpdate.getPrice());
        when(annonceRepository.save(annonceToUpdate)).thenReturn(annonceToUpdate);
        Annonce upadted = annonceService.updateAnnonce(id, toUpdate); // annonceToUpdate

        assertEquals("Title 2", upadted.getTitle());
        assertEquals(Type.VEHICULE, upadted.getType());

    }
}