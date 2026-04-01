package com.giftapp.backend.repository;

import com.giftapp.backend.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    Page<Gift> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Gift> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
}