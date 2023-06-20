package com.example.demo.service;

import com.example.demo.dto.AnnonceDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AnnonceMapper;
import com.example.demo.repository.AnnonceRepository;
import com.example.demo.model.Annonce;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<AnnonceDTO> getAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces.stream()
                .map(annonceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AnnonceDTO> getAnnonceById(UUID id) {
        Optional<Annonce> annonce = annonceRepository.findById(id);
        if (annonce.isPresent()) {
            return annonce.map(annonceMapper::toDTO);
        } else {
            throw new NotFoundException("Annonce not found !");
        }
    }

    public List<AnnonceDTO> filterAnnonces(String title, Double priceMin, Double priceMax, Annonce.Type type) {
        List<AnnonceDTO> filteredAnnonces = annonceRepository.findAll().stream()
                .filter(annonce -> (title != null && !title.isEmpty()) ? annonce.getTitle().toLowerCase().contains(title.toLowerCase()) : true)
                .filter(annonce -> type == null || annonce.getType() == type)
                .filter(annonce -> priceMin == null || annonce.getPrice() >= priceMin)
                .filter(annonce -> priceMax == null || annonce.getPrice() <= priceMax)
                .map(annonceMapper::toDTO)
                .collect(Collectors.toList());
        return filteredAnnonces;
    }

    public AnnonceDTO createAnnonce(Annonce annonce) {
        if (annonce.getTitle() == null || annonce.getType() == null || annonce.getDescription() == null) {
            throw new BadRequestException("Invalid Annonce Data !");
        }
        else {
            annonce.setCreatedAt(LocalDate.now());
            Annonce savedAnnonce = annonceRepository.save(annonce);
            return annonceMapper.toDTO(savedAnnonce);
        }
    }

    public AnnonceDTO updateAnnonce(UUID id, Annonce annonce) {
        Optional<Annonce> existingAnnonce = annonceRepository.findById(id);
        if (existingAnnonce.isPresent()) {
            AnnonceDTO annonceDTO = annonceMapper.toDTO(annonce);

            annonceDTO.setTitle(annonce.getTitle());
            annonceDTO.setDescription(annonce.getDescription());
            annonceDTO.setPrice(annonce.getPrice());
            annonceDTO.setType(annonce.getType());

            Annonce annonceToUpdate = annonceMapper.toEntity(annonceDTO);
            Annonce updatedAnnonce = annonceRepository.save(annonceToUpdate);
            return annonceMapper.toDTO(updatedAnnonce);
        } else {
            throw new NotFoundException("Annonce not found with ID: " + id);
        }
    }

    public void deleteAnnonce(UUID id) {
        Optional<Annonce> annonce = annonceRepository.findById(id);
        if (annonce.isPresent()) {
            annonceRepository.deleteById(annonce.get().getId());
        } else {
            throw new NotFoundException("Annonce not found with ID: " + id);
        }
    }
}
