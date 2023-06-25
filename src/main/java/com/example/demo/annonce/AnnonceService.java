package com.example.demo.annonce;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Slf4j
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }
    public List<Annonce> getAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce getAnnonceById(UUID id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
    }

    public List<Annonce> filterAnnonces(String title, Double priceMin, Double priceMax, Type type) {
        // Solution 1: create fillter(repo) + custom query
        return annonceRepository.filterAnnonces(title, type, priceMin, priceMax);

        // Solution 2:
//        Specification<Annonce> spec = annonceSpecification.filterByParams(title, priceMin, priceMax, type);
//        List<Annonce> annonces = annonceRepository.findAll(spec);
//        return annonces.stream()
//                .map(annonceMapper::toDTO)
//                .collect(Collectors.toList());
    }

    public Annonce createAnnonce(Annonce annonce) {
        if (annonce.getTitle() == null || annonce.getType() == null || annonce.getDescription() == null) {
            throw new BadRequestException("Invalid Annonce Data !");
        }
        else {
            annonce.setCreatedAt(LocalDate.now());
            return annonceRepository.save(annonce);
        }
    }

    public Annonce updateAnnonce(UUID id, Annonce annonce) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        annonceExist.setTitle(annonce.getTitle());
        annonceExist.setDescription(annonce.getDescription());
        annonceExist.setPrice(annonce.getPrice());
        annonceExist.setType(annonce.getType());

        return annonceRepository.save(annonceExist);
    }

    public Annonce patchAnnonce(UUID id, Annonce annonce) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        if (!annonce.getTitle().isEmpty()) annonceExist.setTitle(annonce.getTitle());
        if (!annonce.getDescription().isEmpty()) annonceExist.setDescription(annonce.getDescription());

        return annonceRepository.save(annonceExist);
    }

    public boolean deleteAnnonce(UUID id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        annonceRepository.deleteById(id);
        return true;
    }
}
