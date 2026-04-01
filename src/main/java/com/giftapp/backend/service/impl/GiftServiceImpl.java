package com.giftapp.backend.service.impl;

import com.giftapp.backend.dto.GiftDTO;
import com.giftapp.backend.entity.Gift;
import com.giftapp.backend.repository.GiftRepository;
import com.giftapp.backend.service.GiftService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;

    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public List<GiftDTO> getAllGifts() {
        return giftRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GiftDTO addGift(GiftDTO dto) {
        Gift gift = new Gift();
        gift.setName(dto.getName());
        gift.setPrice(dto.getPrice());

        Gift saved = giftRepository.save(gift);
        return convertToDTO(saved);
    }

    private GiftDTO convertToDTO(Gift gift) {
        GiftDTO dto = new GiftDTO();
        dto.setId(gift.getId());
        dto.setName(gift.getName());
        dto.setPrice(gift.getPrice());
        return dto;
    }

    @Override
    public Page<GiftDTO> getGifts(int page, int size) {
        return giftRepository.findAll(PageRequest.of(page, size))
                .map(this::convertToDTO);
    }

    @Override
    public Page<GiftDTO> searchGifts(String name, Double minPrice, Double maxPrice, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Gift> gifts;

        if (name != null) {
            gifts = giftRepository.findByNameContainingIgnoreCase(name, pageRequest);
        } else if (minPrice != null && maxPrice != null) {
            gifts = giftRepository.findByPriceBetween(minPrice, maxPrice, pageRequest);
        } else {
            gifts = giftRepository.findAll(pageRequest);
        }

        return gifts.map(this::convertToDTO);
    }
}