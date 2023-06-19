package com.example.demo.dto;

import com.example.demo.model.Annonce;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceDTO {
    private UUID id;
    private String title;
    private String description;
    private double price;
    @Enumerated(EnumType.STRING)
    private Annonce.Type type;
    LocalDate createdAt;


    private enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }


}
