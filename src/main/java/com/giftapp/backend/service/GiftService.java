package com.giftapp.backend.service;

import com.giftapp.backend.dto.GiftDTO;
import java.util.List;

import com.giftapp.backend.dto.RecommendationRequest;
import com.giftapp.backend.dto.RecommendationResponse;
import org.springframework.data.domain.Page;

public interface GiftService {
    GiftDTO addGift(GiftDTO giftDTO);
    Page<GiftDTO> searchGifts(String name, Double minPrice, Double maxPrice, int page, int size);
    List<RecommendationResponse> recommendGifts(RecommendationRequest request);
}