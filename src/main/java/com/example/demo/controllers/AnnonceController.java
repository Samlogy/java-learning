package com.example.demo.controllers;

import com.example.demo.dto.AnnonceDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Annonce;
import com.example.demo.service.AnnonceService;
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
//        log.info("annonces: "+ annonces);
        return ResponseEntity.status(HttpStatus.OK).body(annonces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AnnonceDTO>> getAnnonceById(@PathVariable UUID id) {
        Optional<AnnonceDTO> annonce = annonceService.getAnnonceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(annonce);
    }

    @PostMapping
    public ResponseEntity<AnnonceDTO> createAnnonce(@RequestBody Annonce annonce) {
        AnnonceDTO newAnnonce = annonceService.createAnnonce(annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAnnonce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {;
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable UUID id, @RequestBody Annonce annonce) {
        AnnonceDTO updatedAnnonce = annonceService.updateAnnonce(id, annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAnnonce);
    }

//    @GetMapping("/exception")
//    public void triggerException() {
//        if (true) {
//            throw new NotFoundException("Custom exception occurred!");
//        }
//    }
}
