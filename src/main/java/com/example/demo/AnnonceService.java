package com.example.demo;


import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    public Annonce getAnnonceById(Long id) {
        return annonceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
    }

    public Annonce createAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

//    public Annonce updatePost(Long id, Annonce updatedAnnonce) {
//        Annonce existingAnnonce = annonceRepository.findById(Math.toIntExact(id))
//                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
//
//        existingAnnonce.setTitle(updatedAnnonce.getTitle());
//        existingAnnonce.s(updatedAnnonce.getContent());
//        return annonceRepository.save(updatedAnnonce);
//    }
//
//    public void deleteAnnonce(Long id) {
//        Annonce annonce = annonceRepository.findById(Math.toIntExact(id))
//                .orElseThrow(() -> new ResourceNotFoundException("Annonce not found with id: " + id));
//
//        annonceRepository.delete(annonce);
//    }
}
