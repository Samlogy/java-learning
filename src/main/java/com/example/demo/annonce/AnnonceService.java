package com.example.demo.annonce;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final AnnonceMapper annonceMapper;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository, AnnonceMapper annonceMapper) {
        this.annonceRepository = annonceRepository;
        this.annonceMapper = annonceMapper;
    }
    public List<Annonce> getAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces;
    }

    public Annonce getAnnonceById(UUID id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        return annonce;
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

    public Annonce createAnnonce(AnnonceDTO dto) {
        if (dto.getTitle() == null || dto.getType() == null || dto.getDescription() == null) {
            throw new BadRequestException("Invalid Annonce Data !");
        }
        else {
            Annonce annonce = annonceMapper.toEntity(dto);
            annonce.setCreatedAt(LocalDate.now());
            Annonce savedAnnonce = annonceRepository.save(annonce);
            return savedAnnonce;
        }
    }

    public Annonce updateAnnonce(UUID id, AnnonceDTO dto) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        Annonce annonce = annonceMapper.toEntity(dto);

        annonceExist.setTitle(annonce.getTitle());
        annonceExist.setDescription(annonce.getDescription());
        annonceExist.setPrice(annonce.getPrice());
        annonceExist.setType(annonce.getType());

        Annonce updatedAnnonce = annonceRepository.save(annonceExist);
        return updatedAnnonce;

    }

    public Annonce patchAnnonce(UUID id, AnnonceDTO dto) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        Annonce annonce = annonceMapper.toEntity(dto);

        if (!annonce.getTitle().isEmpty()) annonceExist.setTitle(annonce.getTitle());
        if (!annonce.getDescription().isEmpty()) annonceExist.setDescription(annonce.getDescription());

        Annonce patchedAnnonce = annonceRepository.save(annonceExist);
        return patchedAnnonce;

    }

    public void deleteAnnonce(UUID id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        annonceRepository.deleteById(annonce.getId());
    }
}
