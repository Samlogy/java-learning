package com.example.demo.annonce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, UUID>{
    Annonce findAnnonceById(UUID id);

    @Query("SELECT a FROM Annonce a " +
            "WHERE (:title IS NULL OR a.title LIKE %:title%) " +
            "AND (:type IS NULL OR a.type = :type) " +
            "AND (:priceMin IS NULL OR a.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR a.price <= :priceMax)")
    Page<Annonce> filterAnnonces(String title,
                                 Type type,
                                 Double priceMin,
                                 Double priceMax,
                                 Pageable pageable);

    Page<Annonce> findByTitle(String title, Pageable pageable); // pagination

    List<Annonce> findByTitle(String title, Sort sort); // sort
}