package com.example.demo.mapper;

import com.example.demo.dto.AnnonceDTO;
import com.example.demo.model.Annonce;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMapper {

    public AnnonceDTO toDTO(Annonce annonce) {
        AnnonceDTO annonceDTO = new AnnonceDTO();
        annonceDTO.setId(annonce.getId());
        annonceDTO.setTitle(annonce.getTitle());
        annonceDTO.setDescription(annonce.getDescription());
        annonceDTO.setPrice(annonce.getPrice());
        annonceDTO.setType(annonce.getType());
        annonceDTO.setCreatedAt(annonce.getCreatedAt());
        return annonceDTO;
    }

    public Annonce toEntity(AnnonceDTO annonceDTO) {
        Annonce annonce = new Annonce();
        annonce.setId(annonceDTO.getId());
        annonce.setTitle(annonceDTO.getTitle());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setPrice(annonceDTO.getPrice());
        annonce.setType(annonceDTO.getType());
        annonce.setCreatedAt(annonceDTO.getCreatedAt());
        return annonce;
    }
}