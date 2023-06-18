package com.example.demo.controllers;

import com.example.demo.model.Annonce;
import com.example.demo.service.AnnonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Annonce>> getAnnonces() {
        List<Annonce> annonces = annonceService.getAnnonces();
//        log.info("annonces: "+ annonces);
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
        return ResponseEntity.status(HttpStatus.CREATED).body(annonce);
    }

//    @ExceptionHandler(GlobalExceptionHandler.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(GlobalExceptionHandler ex) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }
}
