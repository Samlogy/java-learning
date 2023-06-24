package com.example.demo.annonce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnonceController.class)
public class anoncesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnonceService annonceService;

    @Test
    public void testGetAnnonces() throws Exception {
        Annonce annonce = new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        when(annonceService.getAnnonces()).thenReturn(Collections.singletonList(annonce));
        mockMvc.perform(get("/api/annonce"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFilterAnnonces() throws Exception {
        Annonce a1 = new Annonce("Title 1", "description 1 ...", 100.0, Type.EMPLOI);
        Annonce a2 = new Annonce("Title 2", "description 2 ...", 200.0, Type.IMMOBILIER);
        Annonce a3 = new Annonce("Title 3", "description 3 ...", 100.0, Type.VEHICULE);

        // title != null
        List<Annonce> expected1 = List.of(a1);
        when(annonceService.filterAnnonces("Title 1", null, null, null)).thenReturn(expected1);
        mockMvc.perform(get("/api/annonce/filter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());

        // type != null
        List<Annonce> expected2 = List.of(a2);
        when(annonceService.filterAnnonces(null, null, null, Type.IMMOBILIER)).thenReturn(expected2);
        mockMvc.perform(get("/api/annonce/filter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());

        // priceMin != null
        List<Annonce> expected3 = List.of(a1,a2,a3);
        when(annonceService.filterAnnonces(null, 100.0, null, null)).thenReturn(expected3);
        mockMvc.perform(get("/api/annonce/filter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray());

        // priceMax != null
        List<Annonce> expected4 = List.of(a1,a2,a3);
        when(annonceService.filterAnnonces(null, null, 200.0, null)).thenReturn(expected4);
        mockMvc.perform(get("/api/annonce/filter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray());
    }
}
