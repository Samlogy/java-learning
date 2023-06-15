package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {

    private AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @PostMapping("")
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        Annonce a = annonceService.createAnnonce(annonce);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Annonce>> getAnnonces() {
        return ResponseEntity.status(HttpStatus.OK).body(annonceService.getAnnonces());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Annonce> getAnnonceById(@PathVariable Integer id) {
        Annonce annonce = annonceService. getAnnonceById(Math.toIntExact(id));
        return ResponseEntity.status(HttpStatus.OK).body(annonce);
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
