package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @NotBlank(message = "Price is required")
    private Float price;


    @NotBlank(message = "Type is required")
    private enum type {
        IMMOBILIER("Immobilier"),
        VEHICULE("Véhicule"),
        EMPLOI("Emploi");
        private  String label;

        type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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