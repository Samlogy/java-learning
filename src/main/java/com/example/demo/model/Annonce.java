package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    private double price;

    @Enumerated(EnumType.STRING) // lead db with the correct data type
    private Type type;

    private enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }


}