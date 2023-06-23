package com.example.demo.annonce;

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
    List<Annonce> filterAnnonces(@Param("title") String title,
                                 @Param("type") Type type,
                                 @Param("priceMin") Double priceMin,
                                 @Param("priceMax") Double priceMax);
}