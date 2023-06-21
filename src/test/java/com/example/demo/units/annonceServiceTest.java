package com.example.demo.units;

import com.example.demo.annonce.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class annonceServiceTest {

    @Mock
    AnnonceRepository annonceRepository;

    @InjectMocks
    AnnonceService annonceService;

    private List<Annonce> annoncesExpected;

    @BeforeAll
    public void setup() {
        Annonce a1 = new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce a2 = new Annonce("Title 2", "description 2 ...", 200.0, Type.EMPLOI);
        List<Annonce> annoncesExpected = List.of(a1, a2);
    }

    @Test
    public void testGetAnnonces() {
        Mockito.when(annonceRepository.findAll()).thenReturn(annoncesExpected);

        List<AnnonceDTO> annoncesAcutal = annonceService.getAnnonces();

        assertEquals(2, annoncesAcutal.size());
        assertEquals(Type.EMPLOI, annoncesAcutal.get(0).getType());
        assertEquals(Type.EMPLOI, annoncesAcutal.get(1).getType());
        assertEquals(100.0, annoncesAcutal.get(0).getPrice());
        assertEquals(200.0, annoncesAcutal.get(1).getPrice());
    }
}
