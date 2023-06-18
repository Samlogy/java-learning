package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> getAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces;
    }

    public Annonce getAnnonceById(Integer id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce not found with id: " + /**/id));
    }

    public Annonce createAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public Annonce updateAnnonce(Integer id, Annonce updatedAnnonce) {
        Annonce existingAnnonce = annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce not found with id: " + id));


        existingAnnonce.setTitle(updatedAnnonce.getTitle());
        existingAnnonce.setDescription(updatedAnnonce.getDescription());
        existingAnnonce.setPrice(updatedAnnonce.getPrice());
        existingAnnonce.setType(updatedAnnonce.getType());
        return annonceRepository.save(existingAnnonce);
    }

    public void deleteAnnonce(Integer id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce not found with id: " + id));
        annonceRepository.delete(annonce);
    }
}
