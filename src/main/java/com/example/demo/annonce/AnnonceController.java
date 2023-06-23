package com.example.demo.annonce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {
    private final AnnonceService annonceService;
    private final AnnonceMapper annonceMapper;

    @Autowired
    public AnnonceController(AnnonceService annonceService, AnnonceMapper annonceMapper) {
        this.annonceService = annonceService;
        this.annonceMapper = annonceMapper;
    }

    @GetMapping
    public ResponseEntity<List<Annonce>> getAnnonces() {
        List<Annonce> annonces = annonceService.getAnnonces();
        List<AnnonceDTO> dto = annonces.stream().map(annonceMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(annonces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDTO> getAnnonceById(@PathVariable UUID id) {
        Annonce annonce = annonceService.getAnnonceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(annonceMapper.toDTO(annonce));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnnonceDTO>> filterAnnonces(@RequestParam(name = "title", required = false) String title,
                                                        @RequestParam(name = "priceMin", required = false) Double priceMin,
                                                        @RequestParam(name = "priceMax", required = false) Double priceMax,
                                                        @RequestParam(name = "type", required = false) Type type) {
        List<Annonce> annonces = annonceService.filterAnnonces(title, priceMin, priceMax, type);
        List<AnnonceDTO> dto = annonces.stream().map(annonceMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<AnnonceDTO> createAnnonce(@RequestBody AnnonceDTO annonce) {
        Annonce createdAnnonce = annonceService.createAnnonce(annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(createdAnnonce));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable UUID id) {;
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable UUID id, @RequestBody AnnonceDTO annonce) {
        Annonce updatedAnnonce = annonceService.updateAnnonce(id, annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(updatedAnnonce));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnnonceDTO> patchAnnonce(@PathVariable UUID id, @RequestBody AnnonceDTO annonce) {
        Annonce patchedAnnonce = annonceService.patchAnnonce(id, annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(patchedAnnonce));
    }
}
