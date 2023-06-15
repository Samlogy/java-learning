package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

//@Builder
//@Data // generates getters, setters, toString, equals, hashCode
@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    private double price;

    @Enumerated(EnumType.STRING)
    private Type type;


    public enum Type {
        IMMOBILIER,
        VEHICULE,
        EMPLOI
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Annonce annonce)) return false;
        return Objects.equals(id, annonce.id) && Objects.equals(title, annonce.title) && Objects.equals(description, annonce.description) && Objects.equals(price, annonce.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price);
    }
}