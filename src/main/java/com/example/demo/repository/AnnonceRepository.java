package com.example.demo.repository;

import com.example.demo.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {
    Annonce findAnnonceById(UUID id);
}