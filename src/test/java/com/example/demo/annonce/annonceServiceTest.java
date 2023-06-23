package com.example.demo.annonce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
}