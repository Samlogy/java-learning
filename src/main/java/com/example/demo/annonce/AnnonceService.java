package com.example.demo.annonce;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

    public AnnonceDTO getAnnonceById(UUID id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        return annonceMapper.toDTO(annonce);
    }

    public List<AnnonceDTO> filterAnnonces(String title, Double priceMin, Double priceMax, Annonce.Type type) {
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces.stream()
                .filter(annonce -> title == null || title.isEmpty() || annonce.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(annonce -> type == null || annonce.getType() == type)
                .filter(annonce -> priceMin == null || annonce.getPrice() >= priceMin)
                .filter(annonce -> priceMax == null || annonce.getPrice() <= priceMax)
                .map(annonceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AnnonceDTO createAnnonce(AnnonceDTO dto) {
        if (dto.getTitle() == null || dto.getType() == null || dto.getDescription() == null) {
            throw new BadRequestException("Invalid Annonce Data !");
        }
        else {
            Annonce annonce = annonceMapper.toEntity(dto);
            annonce.setCreatedAt(LocalDate.now());
            Annonce savedAnnonce = annonceRepository.save(annonce);
            return annonceMapper.toDTO(savedAnnonce);
        }
    }

    public AnnonceDTO updateAnnonce(UUID id, AnnonceDTO dto) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        Annonce annonce = annonceMapper.toEntity(dto);

        annonceExist.setTitle(annonce.getTitle());
        annonceExist.setDescription(annonce.getDescription());
        annonceExist.setPrice(annonce.getPrice());
        annonceExist.setType(annonce.getType());

        Annonce updatedAnnonce = annonceRepository.save(annonceExist);
        return annonceMapper.toDTO(updatedAnnonce);

    }



    public void deleteAnnonce(UUID id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        annonceRepository.deleteById(annonce.getId());
    }
}
