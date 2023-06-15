package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AnnonceRepository extends CrudRepository<Annonce, Integer> {
    Annonce findAnnonceById(Integer id);
}