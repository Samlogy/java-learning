package com.example.demo.annonce;

import com.example.demo.annonce.Annonce;
import jakarta.persistence.*;

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
    private Annonce.Type type;
    LocalDate createdAt;

    public AnnonceDTO(String title, String description, double price, Annonce.Type type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    private enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }
}