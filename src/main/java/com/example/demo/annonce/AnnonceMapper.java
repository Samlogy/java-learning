package com.example.demo.annonce;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.JavaBean;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@JavaBean
//@Mapper(componentModel = "spring")
public class AnnonceMapper {

//    Annonce map(AnnonceDTO dto);
//    AnnonceDTO mapToDto(Annonce annonce);

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