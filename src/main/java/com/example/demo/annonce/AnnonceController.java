package com.example.demo.annonce;

import com.example.demo.annonce.AnnonceDTO;
import com.example.demo.annonce.Annonce;
import com.example.demo.annonce.AnnonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {
    private final AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping
    public ResponseEntity<List<AnnonceDTO>> getAnnonces() {
        List<AnnonceDTO> annonces = annonceService.getAnnonces();
        return ResponseEntity.status(HttpStatus.OK).body(annonces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AnnonceDTO>> getAnnonceById(@PathVariable UUID id) {
        Optional<AnnonceDTO> annonce = annonceService.getAnnonceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(annonce);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnnonceDTO>> filterAnnonces(@RequestParam(name = "title", required = false) String title,
                                                        @RequestParam(name = "priceMin", required = false) Double priceMin,
                                                        @RequestParam(name = "priceMax", required = false) Double priceMax,
                                                        @RequestParam(name = "type", required = false) Annonce.Type type) {
        List<AnnonceDTO> annonces = annonceService.filterAnnonces(title, priceMin, priceMax, type);
        return ResponseEntity.status(HttpStatus.OK).body(annonces);
    }

    @PostMapping
    public ResponseEntity<AnnonceDTO> createAnnonce(@RequestBody AnnonceDTO annonce) {
        AnnonceDTO newAnnonce = annonceService.createAnnonce(annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAnnonce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable UUID id) {;
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable UUID id, @RequestBody AnnonceDTO annonce) {
        AnnonceDTO updatedAnnonce = annonceService.updateAnnonce(id, annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAnnonce);
    }


}
