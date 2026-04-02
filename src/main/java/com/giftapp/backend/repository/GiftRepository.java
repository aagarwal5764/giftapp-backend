package com.giftapp.backend.repository;

import com.giftapp.backend.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    boolean existsByCategoryIgnoreCaseAndColourIgnoreCaseAndNameIgnoreCase(
            String category, String colour, String name);
    Page<Gift> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Gift> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
    @Query("""
        SELECT g FROM Gift g
        WHERE 
            (g.gender = :gender OR g.gender = 'UNISEX')
            AND g.occasion = :occasion
            AND :age BETWEEN g.minAge AND g.maxAge
            AND (:budget IS NULL OR g.price <= :budget)
        """)
    List<Gift> findRecommendedGifts(
            @Param("gender") String gender,
            @Param("occasion") String occasion,
            @Param("age") int age,
            @Param("budget") Double budget
    );
}