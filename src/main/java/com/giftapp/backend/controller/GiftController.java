package com.giftapp.backend.controller;

import com.giftapp.backend.dto.GiftDTO;
import com.giftapp.backend.dto.RecommendationRequest;
import com.giftapp.backend.dto.RecommendationResponse;
import com.giftapp.backend.service.GiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/gifts")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public Page<GiftDTO> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return giftService.searchGifts(name, minPrice, maxPrice, page, size);
    }

    @PostMapping("/add")
    public GiftDTO add(@RequestBody GiftDTO giftDTO) {
        return giftService.addGift(giftDTO);
    }

    @PostMapping("/recommend")
    public List<RecommendationResponse> recommend(@RequestBody RecommendationRequest request) {
        return giftService.recommendGifts(request);
    }
}