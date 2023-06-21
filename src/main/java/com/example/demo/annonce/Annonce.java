package com.example.demo.annonce;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private double price;
    @Enumerated(EnumType.STRING)
    private Type type;
    LocalDate createdAt;

    public Annonce(String title, String description, double price, Type type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Annonce(UUID id, String title, String description, double price, Type type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }
}