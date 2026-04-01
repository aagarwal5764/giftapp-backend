package com.giftapp.backend.service;

import com.giftapp.backend.dto.GiftDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface GiftService {
    List<GiftDTO> getAllGifts();
    GiftDTO addGift(GiftDTO giftDTO);
    Page<GiftDTO> getGifts(int page, int size);
    Page<GiftDTO> searchGifts(String name, Double minPrice, Double maxPrice, int page, int size);
}