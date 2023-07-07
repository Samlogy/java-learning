package com.example.demo.annonce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/annonce")
@CrossOrigin(origins = {"http://localhost:4200", "http://check-consulting.net:5001/annonce/filter"})
public class AnnonceController {
    private final AnnonceService annonceService;
    private final AnnonceMapper annonceMapper;
    @Autowired
    public AnnonceController(AnnonceService annonceService, AnnonceMapper annonceMapper) {
        this.annonceService = annonceService;
        this.annonceMapper = annonceMapper;
    }

//    @Autowired
//    public AnnonceController(AnnonceService annonceService) {
//        this.annonceService = annonceService;
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAnnonces(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {
        Map<String, Object> annoncePages = annonceService.getAnnonces(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(annoncePages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDTO> getAnnonceById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(annonceMapper.toDTO(annonceService.getAnnonceById(id)));
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterAnnonces(@RequestParam(name = "title", required = false) String title,
                                                        @RequestParam(name = "priceMin", required = false) Double priceMin,
                                                        @RequestParam(name = "priceMax", required = false) Double priceMax,
                                                        @RequestParam(name = "type", required = false) Type type,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {
        Map<String, Object> annoncePages = annonceService.filterAnnonces(title, priceMin, priceMax, type, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(annoncePages);
    }

    @GetMapping("/pagingSorting")
    public ResponseEntity<Map<String, Object>> getAnnoncesPagingSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        Map<String, Object> result = annonceService.getAnnoncesPagingSorting(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping
    public ResponseEntity<AnnonceDTO> createAnnonce(@RequestBody AnnonceDTO dto) {
        Annonce createdAnnonce = annonceService.createAnnonce(annonceMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(createdAnnonce));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable UUID id) {;
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable UUID id, @RequestBody AnnonceDTO dto) {
        Annonce updatedAnnonce = annonceService.updateAnnonce(id, annonceMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(updatedAnnonce));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnnonceDTO> patchAnnonce(@PathVariable UUID id, @RequestBody AnnonceDTO dto) {
        Annonce patchedAnnonce = annonceService.patchAnnonce(id, annonceMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceMapper.toDTO(patchedAnnonce));
    }
}
