package com.giftapp.backend.service;

import com.giftapp.backend.dto.GiftDTO;
import java.util.List;

public interface GiftService {
    List<GiftDTO> getAllGifts();
    GiftDTO addGift(GiftDTO giftDTO);
}