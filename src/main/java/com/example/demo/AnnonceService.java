package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public Iterable<Annonce> getAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce getAnnonceById(Integer id) {
        return annonceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
    }

    public Annonce createAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public Annonce updateAnnonce(Integer id, Annonce updatedAnnonce) {
        Annonce existingAnnonce = annonceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));

        existingAnnonce.setTitle(updatedAnnonce.getTitle());
        existingAnnonce.setDescription(updatedAnnonce.getDescription());
        existingAnnonce.setPrice(updatedAnnonce.getPrice());
        existingAnnonce.setType(updatedAnnonce.getType());
        return annonceRepository.save(existingAnnonce);
    }

    public void deleteAnnonce(Integer id) {
        Annonce annonce = annonceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
        annonceRepository.delete(annonce);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
