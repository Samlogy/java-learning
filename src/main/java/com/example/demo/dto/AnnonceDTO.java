package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
    private Type type;

    private enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }


}
