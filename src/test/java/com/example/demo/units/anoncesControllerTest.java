package com.example.demo.units;

import com.example.demo.annonce.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnonceController.class)
public class anoncesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnonceService annonceService;

    private Annonce annonce;
    private List<AnnonceDTO> annonces;

    @BeforeAll
    public void setup() {
        AnnonceDTO a1 = new AnnonceDTO("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        AnnonceDTO a2 = new AnnonceDTO("Title 2", "description 2 ...", 200.0, Type.EMPLOI);
        List<AnnonceDTO> annonces = List.of(a1, a2);
    }

    @Test
    public void testGetAnnonces() throws Exception {
        when(annonceService.getAnnonces()).thenReturn(annonces);
        mockMvc.perform(get("/api/annonce"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
