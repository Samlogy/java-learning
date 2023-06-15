package com.example.demo;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AnnonceService {
    private AnnonceRepository annonceRepository;

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
        return annonceRepository.save(existingAnnonce);
    }

    public void deleteAnnonce(Integer id) {
        Annonce annonce = annonceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
        annonceRepository.delete(annonce);
    }
}
