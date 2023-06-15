package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    private final AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Annonce>> getAnnonces() {
        Iterable<Annonce> annonces = annonceService.getAnnonces();
        return ResponseEntity.status(HttpStatus.OK).body(annonces);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Annonce> getAnnonceById(@PathVariable Integer id) {
        Annonce annonce = annonceService. getAnnonceById(Math.toIntExact(id));
        return ResponseEntity.status(HttpStatus.OK).body(annonce);
    }

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        Annonce newAnnonce = annonceService.createAnnonce(annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAnnonce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {;
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Integer id, @RequestBody Annonce updatedPost) {
        Annonce annonce = annonceService.updateAnnonce(id, updatedPost);
        return ResponseEntity.status(HttpStatus.OK).body(annonce);
    }

}
